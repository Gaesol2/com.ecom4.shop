package com.ecom4.custom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.custom.service.CustomService;
import com.ecom4.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomController {

	@Autowired
	CustomService customService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		String page = "redirect:/";
		MemberDTO ssKey = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) page = "redirect:/admin/";
			else page="Index";
		} else {
			page="Index";
		}
		return page;
	}
	
	@RequestMapping("/join")
	public String join(HttpServletRequest request, HttpServletResponse response,
						Model model, MemberDTO mdto) {
		return "custom/MemberJoin";
	}
	
	@RequestMapping("/news")
	public String news() {
		return "custom/NewFile";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response,
			Model model, MemberDTO mdto) {
		return "custom/Login";
	}
	
	@RequestMapping("/searchId")
	public String searchId(HttpServletRequest request, HttpServletResponse response,
			Model model, MemberDTO mdto) {
		model.addAttribute("id",1);
		return "custom/Search";
	}

	@RequestMapping("/searchPw")
	public String searchPw(HttpServletRequest request, HttpServletResponse response,
			Model model, MemberDTO mdto) {
		model.addAttribute("id",0);
		return "custom/Search";
	}

	@RequestMapping("/updatePw")
	public String updatePw(HttpServletRequest request, HttpServletResponse response,
			Model model, MemberDTO mdto) {
		model.addAttribute("id",mdto.getMem_id());
		model.addAttribute("pw",mdto.getM_passwd());
		return "custom/Search";
	}
	
	@RequestMapping("/idCheck")
	@ResponseBody //ajax니까
	public int idCheck(HttpServletRequest request, HttpServletResponse response,
			Model model, MemberDTO mdto) {
		int cnt = 0;
		if(mdto.getMem_id()!=null) {
			cnt = memberService.idCheck(mdto.getMem_id());
		}
		return cnt;
	}
	
	@RequestMapping("/registerProc")
	public String registerProc(HttpServletRequest request, HttpServletResponse response,
			Model model, MemberDTO mdto) {
		int r = memberService.memberJoin(mdto);
		return "redirect:/";
	}
	
	
}
