package com.example.myboard.vo;

import lombok.Data;

@Data
public class BoardCommentVO {
    private String boardcommentno;          // 댓글번호
    private String boardcontentno;          // 글번호
    private String userid;                  // 회원아이디
    private String regdate;                 // 작성날짜
    private String commentcontent;          // 댓글내용
    private String wineno;                  // 와인번호

}
