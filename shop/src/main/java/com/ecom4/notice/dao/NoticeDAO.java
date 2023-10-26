package com.ecom4.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom4.notice.dto.NoticeDTO;

@Mapper
public interface NoticeDAO {

	List<NoticeDTO> getNoticeList(NoticeDTO ndto);

	int getTotCnt();

	void generateNotice(NoticeDTO ndto);

	Map<String, Object> getNoticies(NoticeDTO ndto);

	public int updateReadCnt(NoticeDTO ndto);

	int updateProc(NoticeDTO ndto);

	int deleteProc(NoticeDTO ndto);
	
}
