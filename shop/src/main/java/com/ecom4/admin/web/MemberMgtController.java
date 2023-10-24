package com.ecom4.admin.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberMgtController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/memberMgt")
	public String memberMgt(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model, PageDTO pageDto) {
		
		MemberDTO ssKey = null;
		String page = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) page = "admin/Main";
			else page="redirect:/";
		} else {
			page="redirect:/";
		}
		
		Map<String, Object> reSet = memberService.getMembers(mdto, pageDto);
		
		model.addAttribute("memberTot", reSet.get("memberTot"));
		model.addAttribute("members", reSet.get("members"));
		model.addAttribute("pdto", reSet.get("pageDto"));
		model.addAttribute("contentsJsp","MemberList");
		
		return page;
	}
}
