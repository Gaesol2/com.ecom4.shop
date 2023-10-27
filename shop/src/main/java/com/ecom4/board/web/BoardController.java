package com.ecom4.board.web;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom4.board.dto.BoardDTO;
import com.ecom4.board.service.BoardService;
import com.ecom4.common.dto.PageDTO;
import com.ecom4.common.dto.RowInterPage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//JavaBoard에서 작업했던 Action 역할을 하는 객체 - 비즈니스 로직 BIZ를 갖고 있다
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/freeBList")
	public String getArticles(HttpServletRequest request, HttpServletResponse response,
										BoardDTO dto, Model model, PageDTO pageDto){
		

		logger.info("리스트 컨트롤러에 들어왔다");
		
		Map<String, Object> resultSet = boardService.getArticles(dto, pageDto);
		//List<BoardDTO> articles = boardService.getArticles(dto);
		
		model.addAttribute("articles",resultSet.get("articles"));
		model.addAttribute("totalCnt",resultSet.get("totalCnt"));
		model.addAttribute("pageDto",resultSet.get("pageDto"));
		model.addAttribute("pBlock",RowInterPage.PAGE_OF_BLOCK);
		model.addAttribute("contentsJsp","BoardList");
		
		return "board/Main";
	}

	@RequestMapping(value = "/writeForm")
	public String writeForm(HttpServletRequest request, HttpServletResponse response,
			BoardDTO dto, Model model, PageDTO pageDto) {
		logger.info("글쓰기 컨트롤러에 들어왔다");
		logger.info("dto==>"+dto.getBno());
		
		model.addAttribute("article", dto);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("contentsJsp","BoardWriteForm");
		
		return "board/Main";
	}
	
	@RequestMapping(value = "/writeAction")
	public String writeAction(HttpServletRequest request, HttpServletResponse response,
			BoardDTO dto, Model model, PageDTO pageDto) {
		
		dto.setIp(request.getRemoteAddr());
		boardService.writeArticle(dto);
		
		model.addAttribute("pageDto",pageDto);
		
		return "redirect:freeBList?curPage="+pageDto.getCurPage();
	}
	
	@RequestMapping(value = "/content")
	public String getArticle(HttpServletRequest request, HttpServletResponse response,
			BoardDTO dto, Model model, PageDTO pageDto) {
		
		logger.info("글보기에 들어왔다");
		
		BoardDTO article = boardService.getArticle(dto);
		
		model.addAttribute("article", article);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("contentsJsp","BoardContent");
		
		return "board/Main";
	}
	
	@RequestMapping(value = "/update")
	public String updateArticle(HttpServletRequest request, HttpServletResponse response,
			BoardDTO dto, Model model, PageDTO pageDto) {
		
		model.addAttribute("article", dto);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("contentsJsp","BoardUpdate");
		
		return "board/Main";
	}

	@RequestMapping(value = "/updateAction")
	public String updateAction(HttpServletRequest request, HttpServletResponse response,
			BoardDTO dto, Model model, PageDTO pageDto) {
				
		dto.setIp(request.getRemoteAddr());
		int r = boardService.updateArticle(dto);
		String msg ="";
		
		if(r==0) {
			msg = "수정 실패";
		} else if(r==1) {
			msg = "수정 성공";
		}
		
		model.addAttribute("article", dto);
		model.addAttribute("pageDto", pageDto);
		
		model.addAttribute("msg",msg);
		model.addAttribute("url","freeBList?curPage"+pageDto.getCurPage());
		
		return "MsgPage";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteArticle(HttpServletRequest request, HttpServletResponse response,
			BoardDTO dto, Model model, PageDTO pageDto) {
		
		int r = boardService.deleteArticle(dto);
		String msg = "";
		
		if(r==0) {
			msg = "삭제 실패";
		} else if(r==1) {
			msg = "삭제 성공";
		}
		
		model.addAttribute("pageDto",pageDto);
		model.addAttribute("msg", msg);
		model.addAttribute("url","freeBList?curPage="+pageDto.getCurPage());
		
		return "MsgPage";
	}
}