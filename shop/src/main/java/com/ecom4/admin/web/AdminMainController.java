package com.ecom4.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom4.custom.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response,
			Model model, MemberDTO adto) {
		
		MemberDTO ssKey = null;
		String page = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) page = "admin/Index";
			else page="redirect:/";
		} else {
			page="redirect:/";
		}
		
		return page;
	}
	
}
