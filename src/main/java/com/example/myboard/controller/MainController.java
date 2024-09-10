package com.example.myboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.myboard.service.UserService;
import com.example.myboard.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
public String home() {
    return "redirect:/user/index2";
}


    @GetMapping("/user/index2")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserVO result = (UserVO)session.getAttribute("USER");

        if (result != null) {
            model.addAttribute("ID", result.getId());
        }
        
        
        return "/user/index2";
    }

    
}
