package com.ecom4.notice.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.notice.dto.NoticeDTO;
import com.ecom4.notice.service.NoticeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	@RequestMapping("notice")
	public String notice (HttpServletRequest request, HttpServletResponse response,
			Model model, NoticeDTO ndto, PageDTO pdto) {
		
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		Map<String, Object> reSet = new HashMap<>();
		reSet = noticeService.getNoticeList(ndto,pdto);

		if(mdto!=null) {
			if(mdto.getM_role().equals("admin")) {
				page = "admin/Main";
				contentsJsp = "NoticeGanerate";
			} else {
				page = "Main";
				contentsJsp = "custom/NoticeList";
			}			
		} else {
			page = "Main";
			contentsJsp = "custom/NoticeList";
		}
		
		System.out.println("noticeList==================>"+reSet.get("noticeList"));
		session.setAttribute("ssKey", mdto);		
		model.addAttribute("contentsJsp",contentsJsp);
		model.addAttribute("noticeList",reSet.get("noticeList"));
		model.addAttribute("totCnt",reSet.get("totCnt"));
		return page;
	}

	@RequestMapping("noticeProc")
	public String noticeProc (HttpServletRequest request, HttpServletResponse response,
			Model model, NoticeDTO ndto, PageDTO pdto) {
		
		String flag = request.getParameter("flag");
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		if(mdto!=null) {
			if(mdto.getM_role().equals("admin")) {
				switch(flag) {
				case "insert" : {
					noticeService.generateNotice(ndto);
					break;
				}
				}
				page = "admin/Main";
				contentsJsp = "NoticeGanerate";
			} else {
				page = "Main";
				contentsJsp = "custom/NoticeList";
			}			
		} else {
			page = "Main";
			contentsJsp = "custom/NoticeList";
		}
		
		session.setAttribute("ssKey", mdto);		
		model.addAttribute("contentsJsp",contentsJsp);
		return page;
	}
}
