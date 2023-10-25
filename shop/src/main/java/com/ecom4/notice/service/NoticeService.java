package com.ecom4.notice.service;

import java.util.Map;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.notice.dto.NoticeDTO;

public interface NoticeService {

	Map<String, Object> getNoticeList(NoticeDTO ndto, PageDTO pdto);

	void generateNotice(NoticeDTO ndto);

}
