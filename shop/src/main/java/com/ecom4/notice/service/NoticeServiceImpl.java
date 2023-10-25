package com.ecom4.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.notice.dao.NoticeDAO;
import com.ecom4.notice.dto.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDAO noticeDao;
	
	@Override
	public Map<String, Object> getNoticeList(NoticeDTO ndto, PageDTO pdto) {

		Map<String, Object> reSet = new HashMap<>();
		
		int totCnt = noticeDao.getTotCnt();
		List<NoticeDTO> noticeList = noticeDao.getNoticeList(ndto);
		
		reSet.put("totCnt", totCnt);
		reSet.put("noticeList", noticeList);
		
		return reSet;
	}

	@Override
	public void generateNotice(NoticeDTO ndto) {
		noticeDao.generateNotice(ndto);
		
	}

}
