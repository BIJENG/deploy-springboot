package com.example.myboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myboard.service.UserService;
import com.example.myboard.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String userLogin() {
        return "/user/login";
    }

    @PostMapping("/user/loginProcess")
    public String loginProcess(@ModelAttribute UserVO vo, HttpServletRequest request) throws Exception {
        UserVO result = (UserVO)userService.selectOneUser(vo);

        if (result != null) {
            // 로그인 사용자 정보를 세션에 추가.
            HttpSession session= request.getSession();
            session.setAttribute("USER", result);
            return "redirect:/user/index2"; // 로그인 성공 시 index2 페이지로 리다이렉트
        } else {
            return "redirect:/user/login?error=1";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 현재 세션을 가져옵니다.
        HttpSession session = request.getSession(false);
        
        // 세션이 존재하면 세션을 무효화합니다.
        if (session != null) {
            session.invalidate();
        }
        
        // 로그아웃 후에는 보통 로그인 페이지로 리다이렉트합니다.
        return "redirect:/user/login"; // 로그인 페이지의 URL로 변경하세요.
    }

    @GetMapping("/user/join")
    public String userJoin() {
        return "/user/join";
    }

    @PostMapping("/user/joinProcess")
    @ResponseBody
    public boolean joinProcess(@ModelAttribute UserVO vo) throws Exception{
        int result = 0;

        try {
            result = userService.insertOneUser(vo);
        }
        catch(Exception excp)
        {
            System.out.println(excp.getMessage());
            return false;

        }
        
        if (result > 0) {
            return true;
        }
        
        return false;
    }

    // @GetMapping("/user/index2")
    // public String index2()
    // {
    //     return "/user/index2";
    // }

    @GetMapping("/user/recommend")
    public String recommend()
    {
        return "/user/recommend";
    }

    

    
    
}
