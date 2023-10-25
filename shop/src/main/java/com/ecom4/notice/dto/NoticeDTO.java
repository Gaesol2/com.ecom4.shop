package com.ecom4.notice.dto;

import lombok.Data;

@Data
public class NoticeDTO {
	private int noti_no  ;
	private String subject  ;
	private String content  ;
	private int readcount;
	private String writer   ;
	private String vdate    ;
	private String regdate  ;
	private String state    ;
}
