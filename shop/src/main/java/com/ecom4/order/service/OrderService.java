package com.ecom4.order.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.ecom4.order.dto.OrderDTO;

public interface OrderService {

	int insertOrders(Hashtable<Integer, OrderDTO> hCartList);

	Map<String, Object> getOrders(String mem_id);

	OrderDTO customOrDetail(OrderDTO ovo);

	void updateState(OrderDTO ovo);

}
