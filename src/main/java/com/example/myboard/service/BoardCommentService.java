package com.example.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myboard.dao.BoardCommentDAO;
import com.example.myboard.dao.BoardDAO;
import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;

@Service
public class BoardCommentService {
    
    @Autowired
    private BoardCommentDAO dao;


    public int insertBoardCommentContent(BoardCommentVO vo) throws Exception{
        return dao.insertBoardCommentContent(vo);
    }

    public int insertBoardCommentContent2(BoardCommentVO vo) throws Exception{
        return dao.insertBoardCommentContent2(vo);
    }

    public List<BoardCommentVO> getCommentsByBoardContentNo(String boardContentNo) throws Exception{
        return dao.getCommentsByBoardContentNo(boardContentNo);
    }
    
    public List<BoardCommentVO> getCommentsByWineNo(String wineno) throws Exception {
        return dao.getCommentsByWineNo(wineno);
    }
}
