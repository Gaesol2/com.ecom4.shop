package com.ecom4.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.member.service.MemberService;
import com.ecom4.order.service.OrderService;

@Service("memberWrapper")
public class MemberWrapper {

	@Autowired
	MemberService memberService;
	
	@Autowired
	OrderService orderService;

	public int memDelete(MemberDTO custom) {
		int redata = 0;
		boolean r = orderService.deleteOrder(custom);
		
		if(r) {
			redata = memberService.memDelete(custom);
		} else {
			redata = 0; 
		}
			
		return redata;
	}
	
	
}
