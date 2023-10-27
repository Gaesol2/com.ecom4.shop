package com.ecom4.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.order.dao.OrderDAO;
import com.ecom4.order.dto.OrderDTO;
import com.ecom4.order.web.OrderController;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderDAO orderDao;
	
	@Override
	public int insertOrders(Hashtable<Integer, OrderDTO> hCartList) {
		//키를 받아서 해당 키 만큼 주문내역을 리스트로 받아서 데이터베이스 리스트로 저장
		//결제를 하고 ok가 되면 
		Set<Integer> keys = hCartList.keySet();
		List<OrderDTO> list = new ArrayList<>(keys.size());
		Iterator<Integer> iterKeys = keys.iterator();
		while(iterKeys.hasNext()) {
			list.add(hCartList.get(iterKeys.next()));
		}
		return orderDao.insertOrders(list);
	}

	@Override
	public Map<String, Object> getOrders(String mem_id) {
		int orderTot = orderDao.getTotalOrder(mem_id);
		List<OrderDTO> orderList = orderDao.getOrders(mem_id);
		
		Map<String,Object> reSet = new HashMap<>();
		reSet.put("orderTot", orderTot);
		reSet.put("orderList", orderList);
		
		return reSet;
	}

	@Override
	public OrderDTO customOrDetail(OrderDTO ovo) {
		return orderDao.customOrDetail(ovo);
	}

	@Override
	public void updateState(OrderDTO ovo) {
		orderDao.updateState(ovo);
		
	}

	@Override
	public boolean deleteOrder(MemberDTO custom) {
		// 1. 사용자에 해당하는 모든 구매 이력을 가지고 와서 state가 5보다 작은 목록이 있으면 삭제 불가,
		// 그렇지 않으면 모두 삭제하고 return true
		// 2. 일단 조회해서 모두 가져온 후, 비즈니스 로직에서 비교한 후 가능한 경우만 삭제
		//1번 방법 사용
		List<OrderDTO> orderList = orderDao.getOrderOfMember(custom);
		boolean reData = false;
		if(orderList.size()>0) {
			reData = false;
		} else {
			int r = orderDao.deleteOrder(custom);
			if(r>0) reData =  true;
			else reData =  false;
		}
		return reData;
	}

	@Override
	public void orderStateUpdate(ArrayList<String> tdArr) {
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		for(int i = 0; i<tdArr.size();i+=4) {
			OrderDTO odto = new OrderDTO();
			int n=0, no=0;
			String mid = null;
			n = tdArr.get(i).indexOf(":");
			no = Integer.parseInt(tdArr.get(i).substring(n+1));
			odto.setO_no(no);
			
			n = tdArr.get(i+1).indexOf(":");
			no = Integer.parseInt(tdArr.get(i+1).substring(n+1));
			odto.setO_no(no);
					
			n = tdArr.get(i+2).indexOf(":");
			mid = tdArr.get(i+2).substring(n+1);
			odto.setMem_id(mid);
			
			n = tdArr.get(i+3).indexOf(":");
			no = Integer.parseInt(tdArr.get(i+3).substring(n+1));
			odto.setState(no);
			list.add(odto);
		}
		logger.info("orderService==>"+list);
		orderDao.updateORderState(list);
	}

}
