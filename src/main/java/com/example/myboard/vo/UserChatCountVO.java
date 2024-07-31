package com.example.myboard.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserChatCountVO {
    private String user_id;
    private int daily_count;
    private int total_count; 
    private Timestamp startTime;   
}
