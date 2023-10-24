package com.ecom4.wrapper;

import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom4.order.dto.OrderDTO;
import com.ecom4.order.service.OrderService;
import com.ecom4.product.service.ProductService;


@Service("orderWrapper")
public class OrderWrapper {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Transactional //임시
	public HashMap<String, Object> orderProc(OrderDTO ovo, Hashtable<Integer, OrderDTO> hCartList) {

		String url=null;
		String msg=null;
		int r = orderService.insertOrders(hCartList);
		if(r>0) {
			productService.updateStocks(hCartList);
			
			msg = "주문 완료했습니다.";
			url = "orderList";
			hCartList.clear();				
			
		} else {
			msg = "주문 실패했습니다.";
			url = "cartList";
		}
		HashMap <String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("msg", msg);
		map.put("hCartList", hCartList);
		
		return map;
	}

}
