package com.ecom4.board.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom4.board.dto.BoardDTO;


@Mapper
public interface BoardDAO {

	public List<BoardDTO> getArticles(BoardDTO dto);

	public int getTotalCnt();

	public void writeAction(BoardDTO dto);
	
	public void updateReadCnt(BoardDTO dto);

	public BoardDTO getArticle(BoardDTO dto);

	public int updateArticle(BoardDTO dto);
		
	public int deleteArticle(BoardDTO dto);
}
