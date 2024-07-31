package com.example.myboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myboard.dao.UserDAO;
import com.example.myboard.vo.UserVO;

@Service
public class UserService {

    @Autowired
    private UserDAO dao;

    public UserVO selectOneUser(UserVO vo) throws Exception {
        return dao.selectOneUser(vo);

    }

    public int insertOneUser(UserVO vo) throws Exception {
        return dao.insertOneUser(vo);

    }

}
