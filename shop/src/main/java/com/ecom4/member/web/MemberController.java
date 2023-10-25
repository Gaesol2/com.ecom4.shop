package com.ecom4.member.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.member.service.MemberService;
import com.ecom4.wrapper.MemberWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberWrapper memberWrapper;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping("/loginProc")
	public String loginProc(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model) {

		//세션 정보 (ssKey) - 회원정보
		HttpSession session = request.getSession();
		MemberDTO sdto = memberService.getMember(mdto);
		String url = "/";
		String msg;
		
		if(sdto!=null) {
			//회원 맞다
			if(sdto.getM_role().equals("admin")) {
				//관리자용 페이지로 url
				url = "/admin/";
			} else {
				url="/";
			}
			
			MemberDTO ssKey = new MemberDTO();
			ssKey.setMem_id(sdto.getMem_id());
			ssKey.setM_passwd(sdto.getM_passwd());
			ssKey.setM_name(sdto.getM_name());
			ssKey.setM_role(sdto.getM_role());
			
			msg = ssKey.getM_name()+"님 반갑습니다.";
			session.setAttribute("ssKey",ssKey);
		} else {
				msg = "아이디 또는 패스워드가 맞지 않습니다.";				
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "MsgPage";
	}
	
	@RequestMapping("/logoutProc")
	public String logoutProc(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model) {
		HttpSession session = request.getSession();
		
		model.addAttribute("msg","로그아웃 완료");
		model.addAttribute("url","/");
		session.invalidate();
		
		System.out.println("로그아웃");
		return "MsgPage";
	}
	
	@RequestMapping("/searchID")
	public String searchId(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model) {
		MemberDTO id = memberService.searchId(mdto);
		if(id==null) {
			model.addAttribute("msg", "찾으시는 정보가 없습니다.");
		} else {
			model.addAttribute("msg", "찾으시는 아이디는 " + id.getMem_id() + "입니다.");
		}
		model.addAttribute("url","/searchId");
		return "MsgPage";
	}
	
	@RequestMapping("/searchPW")
	public String searchPw(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model) {
		MemberDTO pw = memberService.searchPw(mdto);
		if(pw==null) {
			model.addAttribute("msg","정보가 틀렸습니다.");
			model.addAttribute("contentsJsp","custom/searchPw");	
		} else {
			model.addAttribute("msg","비밀번호를 바꾸세요.");
			model.addAttribute("contentsJsp","custom/UpdatePw");
			model.addAttribute("mdto",mdto);
			model.addAttribute("pw",1);
		}
		return "custom/Search";
	}
	
	@RequestMapping("/updatePW")
	public String updatePw(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model) {
		mdto.setMem_id(request.getParameter("mem_id"));
		mdto.setM_passwd(request.getParameter("m_passwd"));
		memberService.updatePw(mdto);
		model.addAttribute("msg", "비밀번호 변경 완료");
		model.addAttribute("url","/");
		return "MsgPage";
	}
	
	@RequestMapping("/info")
	   public String info(HttpServletRequest request, HttpServletResponse response,
	            MemberDTO mdto, Model model) {
	      HttpSession session = request.getSession();
	      mdto = (MemberDTO) session.getAttribute("ssKey");
	      
	        String url=null;
	       String msg=null;
	       String page=null;

	       if(mdto != null) {
	          MemberDTO member = memberService.getInfo(mdto);
	 
	           model.addAttribute("member", member);
	            model.addAttribute("contentsJsp","custom/Info");
	            session.setAttribute("ssKey", mdto);
	         
	            page="Main";

	         } else {
	            msg = "로그인 먼저 필요합니다.";
	            url="/login";
	            
	            model.addAttribute("msg",msg);
	            model.addAttribute("url",url);
	            page="MsgPage";
	         }
	         session.setAttribute("ssKey", mdto);
	         
	         return page;
	      }      

	@RequestMapping("/memUpForm")
	public String memUpForm(HttpServletRequest request, HttpServletResponse response,
			 Model model, MemberDTO mdto) {
		HttpSession session = request.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		
		custom.setM_passwd(mdto.getM_passwd());
		String url=null;
		String msg=null;
		String page=null;
		
		System.out.println("custom================>"+ custom);
		
		if(custom != null) {
			mdto = memberService.getMember(custom);
			System.out.println("mdto==============>"+mdto);
			model.addAttribute("mdto", mdto);
			model.addAttribute("contentsJsp","custom/MemberUpForm");
			
			page="Main";
			
		} else {
			msg = "로그인 먼저 필요합니다.";
			url="/login";
			
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
			page="MsgPage";
		}
		session.setAttribute("ssKey", custom);
		
		return page;
	}      
	
	@RequestMapping("/pwCheck")
	public String pwCheck(HttpServletRequest request, HttpServletResponse response,
				MemberDTO mdto, Model model) {
		return "custom/PwCheck";
	}
	
	@RequestMapping("/memUpProc")
	public String memUpProc(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model) {
		HttpSession session = request.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		
		String url=null;
		String msg=null;
		String page=null;
		
		if(custom != null) {
			int r = memberService.memUpProc(mdto);
			if(r>0) {
				msg = "회원 정보가 수정되었습니다. 재 로그인 하세요.";
				session.invalidate();
			} else {
				msg = "회원 정보가 수정되지 않았습니다.";		
				url="/";
			}
						
		} else {
			msg = "로그인 먼저 필요합니다.";
			url="/login";
		}

		page="MsgPage";
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return page;
	}      
	
	@RequestMapping("/memDelete")
	public String memDelete(HttpServletRequest request, HttpServletResponse response,
			MemberDTO mdto, Model model) {
		HttpSession session = request.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		
		String url=null;
		String msg=null;
		String page=null;
		
		if(custom != null) {
			int r = memberWrapper.memDelete(custom);
			if(r>0) {
				msg = "회원 정보가 삭제되었습니다. 재 로그인 하세요.";
				session.invalidate();
			} else {
				msg = "삭제되지 않았습니다.";		
				url="/";
			}
						
		} else {
			msg = "로그인 먼저 필요합니다.";
			url="/login";
		}

		page="MsgPage";
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return page;
	}      
}
