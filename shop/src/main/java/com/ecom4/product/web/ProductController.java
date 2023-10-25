package com.ecom4.product.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.product.dto.ProductDTO;
import com.ecom4.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	//실제로 파일이 저장되는 파일 서버 경로를 가져온 것. properties에 있는 주소 불러와 스트링 변수 resourceLocation에 저장
	@Value("${resources.location}")
	String resourcesLocation;
	
	@RequestMapping ("/productMgt")
	public String productMgr(HttpServletRequest request, HttpServletResponse response,
			Model model, ProductDTO pdto, PageDTO pageDto) {
		
		String page = "redirect:/";
		MemberDTO ssKey = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) page = "admin/ProductMgt";
			else page="redirect:/";
		} else {
			page="redirect:/";
		}
		
		Map<String, Object> reSet = productService.getProductList(pageDto);
		
		model.addAttribute("Products",reSet.get("Products"));
		model.addAttribute("pcnt",reSet.get("pcnt"));
		
		session.setAttribute("ssKey", ssKey);
		
		return page;
	}

	@RequestMapping ("/productInForm")
	public String productInForm(HttpServletRequest request, HttpServletResponse response,
			Model model, ProductDTO pdto, PageDTO pageDto) {
		
		String page = "redirect:/";
		MemberDTO ssKey = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) page = "admin/ProductInForm";
			else page="redirect:/";
		} else {
			page="redirect:/";
		}
		
		session.setAttribute("ssKey", ssKey);
		
		return page;
	}
	
	@RequestMapping ("/productMgtProc")
	public String productInForm(HttpServletRequest request, HttpServletResponse response,
			Model model, ProductDTO pdto, PageDTO pageDto, @RequestParam("image2") MultipartFile file) {
		
		MemberDTO ssKey = null;
		String url = null;
		String msg = null;
		
		int r = 0;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) {
				String flag = request.getParameter("flag");
				if(flag.equals("insert")) {
					pdto.setPath(resourcesLocation);
					r = productService.setProduct(pdto, file);
					if(r>0) {
						msg = "상품 등록 성공";
						url = "";
					} else {
						msg = "상품 등록 실패";
					}
					url="productMgt";
				} else if(flag.equals("update")) {
					pdto.setPath(resourcesLocation);
					r = productService.updateProduct(pdto, file);
				}
			} else {
				url = "redirect:/";
				msg = "잘못된 경로 접근입니다.";
			}
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		session.setAttribute("ssKey", ssKey);
		
		return "MsgPage";
	}
	
	@RequestMapping("/productList")
	public String productList(HttpServletRequest request, HttpServletResponse response,
			Model model, ProductDTO pdto, PageDTO pageDto) {
		
		Map<String, Object> reSet = productService.getProductList(pageDto);
		
		model.addAttribute("pcnt", reSet.get("pcnt"));
		model.addAttribute("Products", reSet.get("Products"));
		model.addAttribute("contentsJsp", "custom/ProductList");
		
		return "Main";
	}
	
	@RequestMapping("/productDetail")
	public String productDetail(HttpServletRequest request, HttpServletResponse response,
			Model model, ProductDTO pdto, PageDTO pageDto) {
		
		String contentsJsp = null;
		String page = null;
		
		HttpSession session = request.getSession();
		
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		if(mdto!=null) {
			if(mdto.getM_role().equals("mem")) {
				contentsJsp = "custom/ProductDetail";
				page = "Main";
			} else if(mdto.getM_role().equals("admin")) {
				contentsJsp = "ProductDetail";
				page = "admin/Main";
			}
		}
		
		ProductDTO product = productService.getProduct(pdto.getP_no());
		
		model.addAttribute("product", product);
		model.addAttribute("contentsJsp", contentsJsp);
		session.setAttribute("ssKey", mdto);
		
		return page;
	}
	
	@RequestMapping ("/productUpForm")
	public String productUpForm(HttpServletRequest request, HttpServletResponse response,
			Model model, ProductDTO pdto, PageDTO pageDto) {
		
		String page = null;
		MemberDTO ssKey = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) {
				page = "admin/ProductUpForm";
				ProductDTO pvo = productService.getProduct(pdto.getP_no());
				page = "admin/ProductUpForm";
				model.addAttribute("pdto",pvo);
			} else page="redirect:/";
		} else {
			page="redirect:/";
		}
		
		session.setAttribute("ssKey", ssKey);
		
		return page;
	}
	
	@RequestMapping("orderCntOfProduct")
	@ResponseBody
	public int orderCntOfProduct(HttpServletRequest request) {
		int pno = Integer.parseInt(request.getParameter("p_no"));
		logger.info("pno===========>"+pno);
		//삭제하기 전에 주문내역 확인
		int r = productService.orderCntOfProduct(pno);
		
		return r;
	}
	
	@RequestMapping ("/productDel")
	public String productDel(HttpServletRequest request, HttpServletResponse response,
			Model model, ProductDTO pdto, PageDTO pageDto) {
		
		String url = null;
		String msg = null;
		
		HttpSession session = request.getSession();
		
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		
		if(mdto!=null) {
			if(mdto.getM_role().equals("mem")) {
				url="/";
			} else if(mdto.getM_role().equals("admin")) {
				int r = productService.productDel(pdto);
				if(r>0) msg = pdto.getP_name() + " 삭제되었습니다.";
				else msg = pdto.getP_name() + "삭제되지 않았습니다.";
				url = "productMgt";
			}
		}else {
			msg = "로그인이 필요합니다.";
			url = "/login";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		session.setAttribute("ssKey", mdto);
		return "MsgPage";
	}
}
