package com.example.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.myboard.vo.BoardCommentVO;
import com.example.myboard.vo.BoardVO;

@Mapper
public interface BoardCommentDAO {
    //댓글

   public int insertBoardCommentContent(BoardCommentVO vo) throws Exception;
   public int insertBoardCommentContent2(BoardCommentVO vo) throws Exception;
    
   public List<BoardCommentVO> getCommentsByBoardContentNo(String boardcontentno) throws Exception;
   public List<BoardCommentVO> getCommentsByWineNo(String wineno) throws Exception;
    
}
