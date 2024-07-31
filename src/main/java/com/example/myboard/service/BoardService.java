package com.example.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myboard.dao.BoardDAO;
import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;
import com.example.myboard.vo.WineVO;

@Service
public class BoardService {
    
    @Autowired
    private BoardDAO dao;

    public List<BoardVO> selectBoardList(BoardVO vo) throws Exception {
        return dao.selectBoardList(vo);
    }

    public BoardVO selectBoardContent(BoardVO vo) throws Exception {
        return dao.selectBoardContent(vo);
    }

    public int updateBoardCount(BoardVO vo) throws Exception {
        return dao.updateBoardCount(vo);
    
    }

    public int updateBoardContent(BoardVO vo) throws Exception {
        return dao.updateBoardContent(vo);
    
    }
    
    public int deleteBoardContent(BoardVO vo) throws Exception {
        return dao.deleteBoardContent(vo);
    
    }

    public int insertBoardContent(BoardVO vo) throws Exception {
        return dao.insertBoardContent(vo);
    
    }

    public BoardVO selectBoardTotalRows() throws Exception{
        return dao.selectBoardTotalRows();
    }

    public List<BoardVO> selectBoardSearch(String boardTitle) throws Exception{
        return dao.selectBoardSearch(boardTitle);
    }
    
public List<WineVO> selectWineSearch(String boardTitle) throws Exception{
        return dao.selectWineSearch(boardTitle);
    }

    //거래하주 게시판
    public List<BoardVO> selectTradeBoardList() throws Exception{
        return dao.selectTradeBoardList();
    }
    
}
