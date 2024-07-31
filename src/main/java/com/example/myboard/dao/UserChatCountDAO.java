package com.example.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;
import com.example.myboard.vo.UserChatCountVO;
import com.example.myboard.vo.UserVO;

@Mapper
public interface UserChatCountDAO {
   
    public int chatCountUpdate(UserVO vo) throws Exception;
    public UserChatCountVO chatCheckLimit(UserVO vo) throws Exception;
    public UserChatCountVO checkStartTime(UserVO vo) throws Exception;
    public int initDailyCount(UserVO vo) throws Exception;

    
}
