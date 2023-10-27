package com.ecom4.order.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom4.cart.service.CartService;
import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.member.web.MemberController;
import com.ecom4.order.dto.OrderDTO;
import com.ecom4.order.service.OrderService;
import com.ecom4.wrapper.OrderWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	
	//트랜잭션을 처리해야하는 로직
	//주문이 성공하면 주문 테이블에 해당 주문이 저장되고
	//상품 테이블에 해당 상품의 재고가 수량만큼 줄어야 한다.
	
	@Autowired
	private OrderService orderService;
	
	
	//서로 다른 업무에 관련하여 트랜잭션 서비스를 하기 위해 사용하는 서버를 Wrapper라고 한다.
	//Order 업무에 Product의 Service와 Member의 Service가 필요하더라도 가져다 쓰는 게 아니라
	//Product와 Member의 Service를 묶은 Wrapper Service를 만들어 사용하는 것
	
	@Autowired
	private OrderWrapper orderWrapper;
	
	@Autowired
	private CartService cartService;
		
	
	@RequestMapping("/orderProc")
	public String orderProc(HttpServletRequest request, HttpServletResponse response,
			OrderDTO ovo, Model model) {
		
		HttpSession session = request.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		
		//장바구니
		Hashtable<Integer, OrderDTO> hCartList = cartService.getCartList();
		
		//주문하고 재고 하나 줄고
		HashMap<String, Object> map = orderWrapper.orderProc(ovo,hCartList);
		
		//model
		model.addAttribute("msg",map.get("msg"));
		model.addAttribute("url",map.get("url"));
		session.setAttribute("ssKey", custom);
		session.setAttribute("hCartList", hCartList);
		
		return "MsgPage";
	}

	@RequestMapping("/orderList")
	public String orderList(HttpServletRequest request, HttpServletResponse response,
			OrderDTO ovo, Model model) {
		
		HttpSession session = request.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		
		String url=null;
		String msg=null;
		String page=null;
		
		Map<String, Object> reSet = null;

		if(custom != null) {
			//고객 세션 정보가 있다면 누구의 장바구니인지 확인
			ovo.setMem_id(custom.getMem_id());
			ovo.setM_role(custom.getM_role());
			reSet = orderService.getOrders(custom.getMem_id());

			model.addAttribute("orderList",reSet.get("orderList"));
			model.addAttribute("orderTot",reSet.get("orderTot"));
			model.addAttribute("contentsJsp", "custom/OrderList");
		
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
	
	@RequestMapping("/customOrDetail")
	public String customOrDetail(HttpServletRequest request, HttpServletResponse response,
			OrderDTO ovo, Model model) {
		String page = null;
		
		HttpSession session = request.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		
		if(custom!=null) {
			ovo = orderService.customOrDetail(ovo);
			model.addAttribute("odto",ovo);
			model.addAttribute("contentsJsp","custom/OrderDetail");
			page = "Main";
		} else {
			String msg = "세션이 종료되었거나 로그인이 안 되어 있습니다.";
			model.addAttribute("msg", msg);
			model.addAttribute("url","/login");
			
			page = "MsgPage";
		}
		
		session.setAttribute("ssKey", custom);
		return page;
	}
	
	@RequestMapping("/orderMgt")
	public String orderMgt(HttpServletRequest request, HttpServletResponse response,
			OrderDTO ovo, Model model) {
		
		HttpSession session = request.getSession();
		MemberDTO admin = (MemberDTO) session.getAttribute("ssKey");
		
		String url=null;
		String msg=null;
		String page=null;
		
		Map<String, Object> reSet = null;

		if(admin != null) {
			//고객 세션 정보가 있다면 누구의 장바구니인지 확인
			ovo.setM_role(admin.getM_role());
			reSet = orderService.getOrders(admin.getMem_id());

			model.addAttribute("orderList",reSet.get("orderList"));
			model.addAttribute("orderTot",reSet.get("orderTot"));
			model.addAttribute("contentsJsp", "./OrderList");
		
			page="admin/Main";

		} else {
			msg = "로그인 먼저 필요합니다.";
			url="/login";
			
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
			page="MsgPage";
		}
		session.setAttribute("ssKey", admin);
		return page;
	}
	
	@RequestMapping("/OrDetailMgt")
	public String OrDetailMgt(HttpServletRequest request, HttpServletResponse response,
			OrderDTO ovo, Model model) {
		String page = null;
		
		HttpSession session = request.getSession();
		MemberDTO admin = (MemberDTO) session.getAttribute("ssKey");
		
		if(admin!=null) {
			ovo = orderService.customOrDetail(ovo);
			model.addAttribute("odto",ovo);
			model.addAttribute("contentsJsp","./OrderDetail");
			page = "admin/Main";
		} else {
			String msg = "세션이 종료되었거나 로그인이 안 되어 있습니다.";
			model.addAttribute("msg", msg);
			model.addAttribute("url","/login");
			
			page = "MsgPage";
		}
		
		session.setAttribute("ssKey", admin);
		return page;
	}

	@RequestMapping("/updateOrder")
	public String updateOrder(HttpServletRequest request, HttpServletResponse response,
			OrderDTO ovo, Model model) {
		
		HttpSession session = request.getSession();
		MemberDTO admin = (MemberDTO) session.getAttribute("ssKey");
		
		String url=null;
		String msg=null;
		String page=null;
		
		if(admin != null) {
			//고객 세션 정보가 있다면 누구의 장바구니인지 확인
			orderService.updateState(ovo);
			page="redirect:/orderMgt";

		} else {
			msg = "로그인 먼저 필요합니다.";
			url="/login";
			
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
			page="MsgPage";
		}
		session.setAttribute("ssKey", admin);
		return page;
	}

	@RequestMapping("/orderMgtProc")
	@ResponseBody
	public void orderMgtProc(HttpServletRequest request, HttpServletResponse response,
			OrderDTO odto, Model model,
			@RequestParam(value="tdArr[]") ArrayList<String> tdArr) {
		
		logger.info("tdArr==>"+tdArr.size()+tdArr.get(4));
		
		try {
			orderService.orderStateUpdate(tdArr);
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info("111111111");
		}
		
	}
}
