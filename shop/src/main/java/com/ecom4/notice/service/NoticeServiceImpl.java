package com.ecom4.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.common.dto.RowInterPage;
import com.ecom4.notice.dao.NoticeDAO;
import com.ecom4.notice.dto.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDAO noticeDao;
	
	@Override
	public Map<String, Object> getNoticeList(NoticeDTO ndto, PageDTO pdto) {

		Map<String, Object> reSet = new HashMap<>();
		
		if(pdto.getCurBlock()<0) pdto.setCurBlock(1);
		if(pdto.getCurPage()<0) pdto.setCurPage(1);
				
		int totCnt = 0;
		List<NoticeDTO> noticeList = null;
		
		if(ndto.getNoti_no()>0) {
			noticeDao.updateReadCnt(ndto);
		}
		totCnt = noticeDao.getTotCnt();
		
		//현재 페이지 계산
		int start = (pdto.getCurPage()-1)*RowInterPage.ROW_OF_PAGE + 1;
		int end = (pdto.getCurPage()*RowInterPage.ROW_OF_PAGE)>totCnt?
				totCnt:pdto.getCurPage()*RowInterPage.ROW_OF_PAGE;
		
		ndto.setStart(start);
		ndto.setEnd(end);
		
		//전체 화면에 나오는 페이지 수
		int pgCnt = (totCnt%RowInterPage.ROW_OF_PAGE==0)?
				totCnt/RowInterPage.ROW_OF_PAGE : totCnt/RowInterPage.ROW_OF_PAGE+1;
		
		//페이지 블럭 계싼
		int pgBlock = (pgCnt%RowInterPage.PAGE_OF_BLOCK==0)?
				pgCnt/RowInterPage.PAGE_OF_BLOCK : pgCnt/RowInterPage.PAGE_OF_BLOCK+1;
		
		int startPg = (pdto.getCurBlock()-1)*RowInterPage.PAGE_OF_BLOCK+1;
		int endPg = (pdto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK > pgCnt)?
				pgCnt : pdto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK;
		
		pdto.setPgCnt(pgCnt);
		pdto.setPgBlock(pgBlock);
		pdto.setStartPg(startPg);
		pdto.setEndPg(endPg);
		
		noticeList = noticeDao.getNoticeList(ndto);
		
		reSet.put("totCnt", totCnt);
		reSet.put("noticeList", noticeList);
		reSet.put("pageDto", pdto);
		
		return reSet;
	}

	@Override
	public void generateNotice(NoticeDTO ndto) {
		noticeDao.generateNotice(ndto);
		
	}

	@Override
	public NoticeDTO getNotice(NoticeDTO ndto) {
		List<NoticeDTO> noticeList = noticeDao.getNoticeList(ndto);
		return noticeList.get(0);
	}

	@Override
	public int updateProc(NoticeDTO ndto) {
		return noticeDao.updateProc(ndto);
	}

	@Override
	public int deleteProc(NoticeDTO ndto) {
		return noticeDao.deleteProc(ndto);
	}

	@Override
	public Map<String, Object> getNoticies(NoticeDTO ndto, PageDTO pageDto) {
		return noticeDao.getNoticies(ndto);
	}

}
