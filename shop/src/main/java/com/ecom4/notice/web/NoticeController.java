package com.ecom4.notice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.common.dto.RowInterPage;
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
			Model model, NoticeDTO ndto, PageDTO pageDto) {
		
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		Map<String, Object> reSet = new HashMap<>();
		reSet = noticeService.getNoticeList(ndto,pageDto);
		
		if(mdto!=null) {
			if(mdto.getM_role().equals("admin")) {
				page = "admin/Main";
				contentsJsp = "NoticeList";
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
		model.addAttribute("noticeList",reSet.get("noticeList"));
		model.addAttribute("totCnt",reSet.get("totCnt"));
		model.addAttribute("pageDto",reSet.get("pageDto"));
		model.addAttribute("pBlock",RowInterPage.PAGE_OF_BLOCK);
		
		return page;
	}

	@RequestMapping("noticeGenerate")
	public String noticeGenerate (HttpServletRequest request, HttpServletResponse response,
			Model model, NoticeDTO ndto, PageDTO pageDto) {
		
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
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
		
		session.setAttribute("ssKey", mdto);		
		model.addAttribute("contentsJsp",contentsJsp);
		return page;
	}
	
	@RequestMapping("noticeProc")
	public String noticeProc (HttpServletRequest request, HttpServletResponse response,
			Model model, NoticeDTO ndto, PageDTO pageDto) {
		
		String flag = request.getParameter("flag");
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		if(mdto!=null && mdto.getM_role().equals("admin")) {
			
			String msg = null;
			String url = null;
			
			if(mdto.getM_role().equals("admin")) {
				switch(flag) {
				case "insert" : {
					noticeService.generateNotice(ndto);
					page = "redirect:notice";
					break;
				}
				case "update" : {
					page = "MsgPage";
					int r = noticeService.updateProc(ndto);
					if(r>0) msg = "수정이 완료되었습니다.";
					else msg = "수정을 실패했습니다.";
					break;
				}
				case "delete" : {
					page = "MsgPage";
					int r = noticeService.deleteProc(ndto);
					if(r>0) msg = "삭제가 완료되었습니다";
					else msg = "삭제를 실패했습니다.";
					url = "notice";	
					break;
				}
				}
				page = "redirect:notice";
				contentsJsp = "NoticeList";
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
	
	@RequestMapping("noticeDetail")
	public String noticeDetail (HttpServletRequest request, HttpServletResponse response,
			Model model, NoticeDTO ndto, PageDTO pageDto) {
		
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		if(mdto!=null) {
			if(mdto.getM_role().equals("admin")) {
				NoticeDTO notice = noticeService.getNotice(ndto);
				model.addAttribute("notice",notice);
				page = "admin/Main";
				contentsJsp = "./Notice"; 
			} else {
				//Map<String, Object> reSet = noticeService.getNoticies(ndto,pageDto);
				//List<NoticeDTO> noticeList = (List<NoticeDTO>) reSet.get("noticeList");
				//model.addAttribute("notice", noticeList.get(0));
				NoticeDTO notice = noticeService.getNotice(ndto);
				model.addAttribute("notice",notice);
				//고객용에서 조회수 증가
				page = "Main";
				contentsJsp = "custom/Notice";
			}
		} else {
			NoticeDTO notice = noticeService.getNotice(ndto);
			model.addAttribute("notice",notice);
			page = "Main";
			contentsJsp = "custom/Notice";
		}
		
		session.setAttribute("ssKey", mdto);		
		model.addAttribute("contentsJsp",contentsJsp);
		return page;
	}
	
	@RequestMapping("noticeUpForm")
	public String noticeUpForm (HttpServletRequest request, HttpServletResponse response,
			Model model, NoticeDTO ndto, PageDTO pageDto) {
		
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		if(mdto!=null) {
			if(mdto.getM_role().equals("admin")) {
				page = "admin/Main";
				contentsJsp = "NoticeUpForm";
			} else {
				page = "Main";
				contentsJsp = "custom/NoticeList";
			}			
		} else {
			page = "Main";
			contentsJsp = "custom/NoticeList" ;
		}
		
		session.setAttribute("ssKey", mdto);
		model.addAttribute("notice",ndto);
		model.addAttribute("contentsJsp",contentsJsp);
		return page;
	}

	@RequestMapping("noticeUpProc")
	public String noticeUpProc (HttpServletRequest request, HttpServletResponse response,
			Model model, NoticeDTO ndto, PageDTO pageDto) {
		
		String page = null;
		String contentsJsp = null;
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		if(mdto!=null) {
			if(mdto.getM_role().equals("admin")) {
				page = "admin/Main";
				contentsJsp = "Notice";
				noticeService.updateProc(ndto);
			} else {
				page = "Main";
				contentsJsp = "custom/NoticeList";
			}			
		} else {
			page = "Main";
			contentsJsp = "custom/NoticeList" ;
		}
		
		session.setAttribute("ssKey", mdto);
		model.addAttribute("notice",ndto);
		model.addAttribute("contentsJsp",contentsJsp);
		return page;
	}
	
}
