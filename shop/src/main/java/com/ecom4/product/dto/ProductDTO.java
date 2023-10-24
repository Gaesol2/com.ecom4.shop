package com.ecom4.product.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {
	private int rn;
	private int rr;
	private int p_no;
	private int stock;
	private int price;
	private String p_name;
	private String detail;
	private String pr_date;
	private String image;
	private String path;
}
