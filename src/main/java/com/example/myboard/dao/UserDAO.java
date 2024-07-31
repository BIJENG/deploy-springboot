package com.example.myboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.myboard.vo.UserVO;

@Mapper
public interface UserDAO {
    public UserVO   selectOneUser(UserVO vo) throws Exception;
    public int      insertOneUser(UserVO vo) throws Exception;
    
}
