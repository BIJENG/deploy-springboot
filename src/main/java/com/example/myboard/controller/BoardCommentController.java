package com.example.myboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.myboard.service.BoardCommentService;
import com.example.myboard.service.BoardService;
import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;
import com.example.myboard.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class BoardCommentController {
    @Autowired
    private BoardCommentService boardCommentService;
    
    @PostMapping("/board/commentInsertProcess")
    public String boardCommentProcess(HttpServletRequest request, 
                                    @RequestParam("comment_content") String commentContent,
                                    @ModelAttribute BoardCommentVO vo) throws Exception {
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("USER");
        String contentNo = request.getParameter("no");
        vo.setUserid(user.getId());
        vo.setBoardcontentno(contentNo);
        vo.setCommentcontent(commentContent);
        boardCommentService.insertBoardCommentContent(vo);



        return "redirect:/board/content?no=" + contentNo;
    }

    @PostMapping("/board/CommentlistProcess")
    @ResponseBody
    public List<BoardCommentVO> getCommentsByBoardContentNo(@RequestBody Map<String, String> payload) throws Exception {
        String contentNo = payload.get("no");

        // 데이터베이스에서 댓글 목록을 가져옴
        List<BoardCommentVO> comments = boardCommentService.getCommentsByBoardContentNo(contentNo);

        // 제대로 된 데이터가 반환되는지 로그로 확인
        return comments; // JSON으로 반환
    }

    @PostMapping("/board/commentInsertProcess2")
    public String boardCommentProcess2(HttpServletRequest request,
                                        @RequestParam("commentContent") String commentContent,
                                        @RequestParam("wineNo") String wineNo) {    
        try {
            HttpSession session = request.getSession();
            UserVO user = (UserVO) session.getAttribute("USER");

            if (user == null) {
                // 로그인하지 않은 사용자에게 에러 메시지를 전송
                return "redirect:/user/login";
            }
            
            BoardCommentVO vo = new BoardCommentVO();
            vo.setUserid(user.getId());
            vo.setCommentcontent(commentContent);
            vo.setWineno(wineNo); // 와인 번호 설정

            boardCommentService.insertBoardCommentContent2(vo);
            
            // 댓글이 성공적으로 저장되었음을 클라이언트에게 알림
            return "redirect:/board/recommend-detail?wineNo=" + wineNo;
        } catch (Exception e) {
            // 예외가 발생한 경우 에러 메시지를 전송
            return "error-page";
        }
    }

    
    @PostMapping("/board/CommentlistProcess2")
    @ResponseBody
    public List<BoardCommentVO> getCommentsByWineNo(@RequestBody Map<String, String> payload) throws Exception {
        String contentNo = payload.get("no");

        
        List<BoardCommentVO> comments = boardCommentService.getCommentsByWineNo(contentNo);

        
        return comments; 
    }

    

}
