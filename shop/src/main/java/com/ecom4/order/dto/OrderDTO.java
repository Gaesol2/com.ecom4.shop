package com.ecom4.order.dto;

import lombok.Data;

@Data
public class OrderDTO {
	private int o_no;   // 기본키
	private int price;
	private int quantity;
	private String o_regdate;
	private int state;
	private String mem_id;   //외래키
	private int p_no;   //외래키
	private int stock;
	private String p_name;
	private String m_name;
	private String m_role;
	private int amount;
	
	private int startRow = 1;
	private int endRow = 10;
}
