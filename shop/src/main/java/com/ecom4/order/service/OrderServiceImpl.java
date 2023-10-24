package com.ecom4.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom4.order.dao.OrderDAO;
import com.ecom4.order.dto.OrderDTO;

@Service
public class OrderServiceImpl implements OrderService {

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

}
