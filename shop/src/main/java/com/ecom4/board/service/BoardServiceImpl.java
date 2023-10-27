package com.ecom4.board.service;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom4.board.dao.BoardDAO;
import com.ecom4.board.dto.BoardDTO;
import com.ecom4.common.dto.PageDTO;
import com.ecom4.common.dto.RowInterPage;


@Service(value = "boardService")
public class BoardServiceImpl implements BoardService {

	// DAO DI(Dependency Injection)
	@Autowired
	private BoardDAO boardDao;
	
	@Override
	public Map<String, Object> getArticles(BoardDTO dto, PageDTO pdto) {
		Map<String, Object> resultSet = new HashMap<String, Object>();
		
		if(pdto.getCurBlock()<0) pdto.setCurBlock(1);
		if(pdto.getCurPage()<0) pdto.setCurPage(1);
		
		int totalCnt = boardDao.getTotalCnt();
		resultSet.put("totalCnt",totalCnt);
		
		//현재 페이지 계산
		int start = (pdto.getCurPage()-1)*RowInterPage.ROW_OF_PAGE + 1;
		int end = (pdto.getCurPage()*RowInterPage.ROW_OF_PAGE)>totalCnt?
				totalCnt:pdto.getCurPage()*RowInterPage.ROW_OF_PAGE;
		
		dto.setStart(start);
		dto.setEnd(end);
		
		int pgCnt = (totalCnt%RowInterPage.ROW_OF_PAGE==0)?
				totalCnt/RowInterPage.ROW_OF_PAGE : totalCnt/RowInterPage.ROW_OF_PAGE+1;
		
		//페이지 블럭
		int pgBlock = (pgCnt%RowInterPage.PAGE_OF_BLOCK==0)?
				pgCnt/RowInterPage.PAGE_OF_BLOCK : pgCnt/RowInterPage.PAGE_OF_BLOCK+1;
		
		int startPg = (pdto.getCurBlock()-1)*RowInterPage.PAGE_OF_BLOCK+1;
		int endPg = (pdto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK > pgCnt)?
				pgCnt : pdto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK;
		
		pdto.setPgCnt(pgCnt);
		pdto.setPgBlock(pgBlock);
		pdto.setStartPg(startPg);
		pdto.setEndPg(endPg);
		
		resultSet.put("pageDto",pdto);
		resultSet.put("articles", boardDao.getArticles(dto));
		
		return resultSet;
	}
	
	@Override
	public void writeArticle(BoardDTO dto){
		boardDao.writeAction(dto);
	}

	@Transactional // 둘 중에 하나만 되지 않고, 둘 다 되거나 둘 다 안 되게 할 때 쓰는 것
	@Override
	public BoardDTO getArticle(BoardDTO dto) {
		//update - readcount
		
		boardDao.updateReadCnt(dto);
		
		//getArticle
		BoardDTO article = boardDao.getArticle(dto);
		
		return article;
	}

	@Override
	public int updateArticle(BoardDTO dto) {
		return boardDao.updateArticle(dto);
	}

	@Override
	public int deleteArticle(BoardDTO dto) {
		return boardDao.deleteArticle(dto);
	}
}
