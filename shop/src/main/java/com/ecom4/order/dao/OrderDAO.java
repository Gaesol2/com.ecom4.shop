package com.ecom4.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.order.dto.OrderDTO;

@Mapper
public interface OrderDAO {

	int insertOrders(List<OrderDTO> list);

	List<OrderDTO> getOrders(String mem_id);

	int getTotalOrder(String mem_id);

	OrderDTO customOrDetail(OrderDTO ovo);

	void updateState(OrderDTO ovo);

	List<OrderDTO> getOrderOfMember(MemberDTO custom);

	int deleteOrder(MemberDTO custom);

}
