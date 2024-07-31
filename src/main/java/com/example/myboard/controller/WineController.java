// WineController.java
package com.example.myboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myboard.service.WineServiceImpl;
import com.example.myboard.vo.WineVO;

@RestController
public class WineController {

    private final WineServiceImpl wineService;

    @Autowired
    public WineController(WineServiceImpl wineService) {
        this.wineService = wineService;
    }

    // @GetMapping("/wines")
    // public String showWines(Model model) {
    //     List<WineVO> wines = wineService.get8Wines();
    //     model.addAttribute("wines", wines);
    //     return "wines"; // 이동할 HTML 파일명
    // }
}

