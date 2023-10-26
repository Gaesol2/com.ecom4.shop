package com.ecom4.notice.service;

import java.util.Map;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.notice.dto.NoticeDTO;

public interface NoticeService {

	Map<String, Object> getNoticeList(NoticeDTO ndto, PageDTO pdto);

	void generateNotice(NoticeDTO ndto);

	NoticeDTO getNotice(NoticeDTO ndto);

	int updateProc(NoticeDTO ndto);

	int deleteProc(NoticeDTO ndto);

	Map<String, Object> getNoticies(NoticeDTO ndto, PageDTO pageDto);

}
