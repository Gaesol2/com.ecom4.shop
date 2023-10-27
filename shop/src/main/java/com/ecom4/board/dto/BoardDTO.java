package com.ecom4.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private int start;
	private int end;
	
	private int rn;
	private int bno;
	private int nbno;
	private int bref;
	private int bstep;
	private int blevel;
	private int readcount;
	private String subject;  
	private String content;  
	private String writer;  
	private String regdate;  
	private String ip;
	private String passwd;
}
