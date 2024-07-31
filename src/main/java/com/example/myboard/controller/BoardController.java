package com.example.myboard.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.myboard.service.BoardService;
import com.example.myboard.service.ChatGPTApiClient;
import com.example.myboard.service.WineService;
import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;
import com.example.myboard.vo.UserVO;
import com.example.myboard.vo.WineVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private WineService wineService;
    
    @Autowired
    private ChatGPTApiClient chatGPTservice;

    @GetMapping("/board/trade")
    public String trade() {
     
        return "/board/trade";
    }

    @GetMapping("/board/list")
    public String boardList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("USER");

        if (user != null) {
            model.addAttribute("ID", user.getId());
        } else {
            model.addAttribute("ID", "null");
        }       
        return "/board/list";
    }

    @PostMapping("/board/listProcess")
    @ResponseBody
    public List<BoardVO> boardListProcess(@RequestBody BoardVO vo) throws Exception {
        List<BoardVO> list = boardService.selectBoardList(vo);
        return list;

    }

    @GetMapping("/board/content")
    public String boardContent(@ModelAttribute BoardVO vo,
                               HttpServletRequest request,
                               Model model) throws Exception {
        int count = 0;
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("USER");

        BoardVO result = boardService.selectBoardContent(vo);

            
        if (user == null) { //guest
            
            boardService.updateBoardCount(vo);

            // DB에서 1증가된 VCount를 vo에도 적용한다.
            count = Integer.parseInt(result.getVcount());
            count++;
            result.setVcount(Integer.toString(count));            
            
            model.addAttribute("readOnly", true);

        }
        else {
            if (user.getId().equals(result.getId())) { // 로그인 사용자의 글인 경우
                model.addAttribute("readOnly", false);
            }
            else {
                boardService.updateBoardCount(vo);

                // DB에서 1증가된 VCount를 vo에도 적용한다.
                count = Integer.parseInt(result.getVcount());
                count++;
                result.setVcount(Integer.toString(count));            
            
                model.addAttribute("readOnly", true);
            }

        }

        model.addAttribute("vo", result);
        
        return "/board/content";
    }
    
    @PostMapping("/board/saveProcess")
    @ResponseBody
    public boolean boardSaveProcess(@ModelAttribute BoardVO vo) throws Exception{

        int result = 0;
        try {
            result = boardService.updateBoardContent(vo);
        }
        catch (Exception excp) {
            return false;
        }

        if (result > 0) {
            return true;
        }            

        return false;
    }
    

    @PostMapping("/board/deleteProcess")
    @ResponseBody
    public boolean boardDeleteProcess(@ModelAttribute BoardVO vo) throws Exception{
        
        int result = 0;
        try {
            result = boardService.deleteBoardContent(vo);
            
        }
        catch (Exception excp) {
            return false;
        }

        if (result > 0) {

            return true;
        }            

        return false;
    }

    @GetMapping("/board/newContent")
    public String boardNewContent() {
        return "/board/newContent";
    }
    
    @PostMapping("/board/insertProcess")
    @ResponseBody
    public boolean boardInsertProcess(@ModelAttribute BoardVO vo,
                                  @RequestParam("image") MultipartFile image,
                                  HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();     
        UserVO user = (UserVO) session.getAttribute("USER");
        vo.setId(user.getId());

        // 정적 리소스 디렉터리 경로 가져오기
        String uploadDir = request.getServletContext().getRealPath("/imgs");
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String imgpath = null;
        if (image != null && !image.isEmpty()) {
            // 타임스탬프를 사용하여 고유한 파일 이름 생성
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = formatter.format(new Date());
            String originalFilename = image.getOriginalFilename();
            String newFilename = timestamp + "_" + originalFilename;

            // 파일을 정적 리소스 디렉터리에 저장
            File dest = new File(uploadDir, newFilename);
            image.transferTo(dest);

            // 웹 경로로 이미지 경로 설정
            imgpath = "/imgs/" + newFilename;
    }

        // BoardVO 객체의 imgpath에 경로 설정
        vo.setImgpath(imgpath);

        int result = boardService.insertBoardContent(vo);
        return result > 0;
    }

    @PostMapping("/board/totalrows")
    @ResponseBody
    public String boardRowCount() throws Exception {
        BoardVO vo = boardService.selectBoardTotalRows();
        return vo.getTotalRows();

    }
    
    @GetMapping("/board/recommend")
    public String recommend(@RequestParam(name = "cbtest1", defaultValue = "false") boolean cbtest1,
                            @RequestParam(name = "cbtest2", defaultValue = "false") boolean cbtest2,
                            @RequestParam(name = "cbtest3", defaultValue = "false") boolean cbtest3,
                            @RequestParam(name = "cbtest4", defaultValue = "false") boolean cbtest4,
                            Model model) {
        // 체크박스의 상태에 따라 다른 와인 리스트를 가져옴
        List<WineVO> wines;
        if (cbtest1 || cbtest2 || cbtest3 || cbtest4) {
            wines = wineService.getWinesByCheckboxes(cbtest1, cbtest2, cbtest3, cbtest4);
        } else {
            wines = wineService.get8Wines(); // 기존의 8개 와인 리스트 반환
        }
        System.out.println(wines);
        model.addAttribute("wines", wines);
        return "/board/recommend";
    }

    @GetMapping("/board/recommend/keyword")
    public String getWinesByKeyword(@RequestParam(name = "keyword") String keyword, Model model) {

        String gptPick;

        gptPick = chatGPTservice.wineTag(keyword);
        
        List<WineVO> wines = wineService.get8Wines(gptPick);
        System.out.println(wines);
        model.addAttribute("wines", wines);
        return "board/recommend";
    }
    
    @GetMapping("/board/recommend-detail")
    public String recommendDetail(@RequestParam(name = "wineNo") int wineNo, Model model) {
        // 와인 상세 정보를 가져오는 메서드 호출
        WineVO wine = wineService.getWineByNo(wineNo);
        
        // 가져온 와인 정보를 모델에 추가하여 뷰로 전달
        model.addAttribute("wine", wine);
        
        // 와인 상세 정보를 보여주는 뷰로 이동
        return "/board/recom_content";
    }

    @GetMapping("/board/rank")
    public String boardRank(@RequestParam(name = "selection", required = false, defaultValue = "famous") String selection, Model model) {
        List<WineVO> wines = wineService.getWinesBySelection(selection);
        model.addAttribute("wines", wines);
        return "/board/rank";
    }

    @PostMapping("/board/tag")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/board/searchResult")
    public String searchResultPage() {
      
        return "/board/searchResult"; // JSP 파일 이름 (예: searchResult.jsp)
    }

    @GetMapping("/board/searchData")
    @ResponseBody
    public List<BoardVO> boardSearch(@RequestParam("query") String query) throws Exception {
        return boardService.selectBoardSearch(query);
    }
    
    @GetMapping("/board/searchWineData")
    @ResponseBody
    public List<WineVO> wineSearch(@RequestParam("query") String query) throws Exception {
        
        return boardService.selectWineSearch(query);
    }

    @GetMapping("/board/tradeListProcess")
    @ResponseBody
    public List<BoardVO> tradeListProcess() throws Exception{
        List<BoardVO> result = boardService.selectTradeBoardList();
        System.out.println(result);

        return result;
    }


    @GetMapping("/board/tradeContent")
    public String boardTradeContent(@ModelAttribute BoardVO vo,
                               HttpServletRequest request,
                               Model model) throws Exception {
        int count = 0;
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("USER");

        BoardVO result = boardService.selectBoardContent(vo);

            
        if (user == null) { //guest
            
            boardService.updateBoardCount(vo);

            // DB에서 1증가된 VCount를 vo에도 적용한다.
            count = Integer.parseInt(result.getVcount());
            count++;
            result.setVcount(Integer.toString(count));            
            
            model.addAttribute("readOnly", true);

        }
        else {
            if (user.getId().equals(result.getId())) { // 로그인 사용자의 글인 경우
                model.addAttribute("readOnly", false);
            }
            else {
                boardService.updateBoardCount(vo);

                // DB에서 1증가된 VCount를 vo에도 적용한다.
                count = Integer.parseInt(result.getVcount());
                count++;
                result.setVcount(Integer.toString(count));            
            
                model.addAttribute("readOnly", true);
            }

        }

        model.addAttribute("vo", result);
        
        return "/board/tradeContent";
    }
  
}
