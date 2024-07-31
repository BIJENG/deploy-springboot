package com.example.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;
import com.example.myboard.vo.WineVO;

@Mapper
public interface BoardDAO {
    public List<BoardVO> selectBoardList(BoardVO vo) throws Exception;
    public BoardVO selectBoardContent(BoardVO vo) throws Exception;
    public int updateBoardCount(BoardVO vo) throws Exception;
    public int updateBoardContent(BoardVO vo) throws Exception;
    public int deleteBoardContent(BoardVO vo) throws Exception;
    public int insertBoardContent(BoardVO vo) throws Exception;
    public BoardVO selectBoardTotalRows() throws Exception;

    //댓글

   public int insertBoardCommentContent(BoardCommentVO vo) throws Exception;
   //검색 
   public List<BoardVO> selectBoardSearch(String boardTitle) throws Exception;
   public List<WineVO> selectWineSearch(String boardTitle) throws Exception;

   //거래하주 게시판
   public List<BoardVO> selectTradeBoardList() throws Exception;
    
}
