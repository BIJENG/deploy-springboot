package com.example.myboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myboard.service.ChatGPTApiClient;
import com.example.myboard.service.WineService;
import com.example.myboard.service.UserChatCountService;
import com.example.myboard.vo.UserChatCountVO;
import com.example.myboard.vo.UserVO;
import com.example.myboard.vo.WineVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class ChatController {

    @Autowired
    private WineService wineService;

    @Autowired
    private UserChatCountService UserChatCountService;

    private final ChatGPTApiClient chatGPTApiClient;

    public ChatController(ChatGPTApiClient chatGPTApiClient) {
        this.chatGPTApiClient = chatGPTApiClient;
    }

    @GetMapping("/chat")
    public String index() {
        return "chat";
    }

    @GetMapping("/chatPage")
    public String indexchat() {
        return "chatPage";
    }

    // 세션 정보를 사용하여 프롬프트를 보내는 메서드
    @PostMapping("/chat")
    @ResponseBody
    public Map<String, Object> chat(@RequestBody Map<String, String> requestBody, HttpSession session) {
        // 요청 본문에서 프롬프트를 가져옵니다.
        String prompt = requestBody.get("prompt");
        System.out.println(prompt);
        // ChatGPTApiClient에 프롬프트를 전달합니다.
        String response = chatGPTApiClient.chatbot(prompt);

        // JSON 형태로 응답 반환(chat bot)
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("choices", response);
        jsonResponse.put("status", "success");
        return jsonResponse;
    }

    @PostMapping("/checkLimit")
    @ResponseBody
    public Boolean checkLimit(HttpServletRequest request) throws Exception {


        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("USER");

        if(UserChatCountService.chatCheckLimit(user))
        {   
            System.out.println("리미트 입니다");
            return false;
        }
        else
        {
            System.out.println("리미트 통과!");
            
            if(UserChatCountService.chatCountUpdate(user)){
                System.out.println("채팅기록 횟수가 업데이트 됐습니다.");
            }
            else
            {
                System.out.println("채팅기록 횟수 업데이트가 실패 됐습니다.");
            }

            return true;
        }

    }
    

    
}
