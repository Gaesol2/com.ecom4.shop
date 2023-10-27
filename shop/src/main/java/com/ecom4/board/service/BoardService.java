package com.ecom4.board.service;
import java.util.List;
import java.util.Map;

import com.ecom4.board.dto.BoardDTO;
import com.ecom4.common.dto.PageDTO;


public interface BoardService {

	Map<String, Object> getArticles(BoardDTO dto, PageDTO pdto);

	void writeArticle(BoardDTO dto);
	
	BoardDTO getArticle(BoardDTO dto);

	int updateArticle(BoardDTO dto);

	int deleteArticle(BoardDTO dto);
}