package com.example.myboard.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myboard.dao.BoardDAO;
import com.example.myboard.dao.UserChatCountDAO;
import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;
import com.example.myboard.vo.UserChatCountVO;
import com.example.myboard.vo.UserVO;

@Service
public class UserChatCountService {
    
    @Autowired
    private UserChatCountDAO dao;


    public Boolean chatCountUpdate(UserVO vo) throws Exception {
        
        int result = dao.chatCountUpdate(vo);

        if(result > 0)
        {
            return true;
        }

        return false;
    }

    public Boolean chatCheckLimit(UserVO vo) throws Exception{
        Timestamp start_Time = checkStartTime(vo);
        int result = dao.chatCheckLimit(vo).getDaily_count();

        //시간 저장
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startDateTime = start_Time.toLocalDateTime();
        
        //날짜 비교 메서드 ChronoUnit (분을 비교 하는게아닌 날짜 자체를 비교하여 분을 리턴함)
        long minutesBetween = ChronoUnit.MINUTES.between(startDateTime, currentTime);

        if(result >= 3){
            
            //2분차이일때 초기화 되도록 함.
            if(minutesBetween > 2)
            {
                //daily_count reset
                initDailyCount(vo);
                System.out.println("초기화 성공");
                return false;
            }

            return true;
        }
        else
        {
            return false;
        }
            

    }
    
    public Timestamp checkStartTime(UserVO vo) throws Exception{
        System.out.println(dao.checkStartTime(vo).getStartTime());
        return dao.checkStartTime(vo).getStartTime();
    }

    public int initDailyCount(UserVO vo) throws Exception{
        return dao.initDailyCount(vo);
    }
    
}
