package com.example.myboard.vo;

import lombok.Data;

@Data
public class ApiVO {
    private String wine;    // name에 매핑
    private String winery;  // producer에 매핑
    private Long id;        // no에 매핑
    private String image;   // url에 매핑
    private String location; // origin에 매핑
}