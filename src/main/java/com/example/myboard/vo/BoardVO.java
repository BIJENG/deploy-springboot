package com.example.myboard.vo;

import lombok.Data;

@Data
public class BoardVO {
    private String no;          // 번호
    private String id;          // 회원아이디
    private String name;        // 회원이름
    private String title;       // 제목
    private String content;     // 내용
    private String regdate;     // 등록일
    private String vcount;      // 조회수
    private String imgpath;

    private String totalRows;    //전체 행의 개수
    private String page;        //페이지
    private String rowsPerPage;//한번에 보여줄 행의 수 
    private String tradeStatus;

    
}
