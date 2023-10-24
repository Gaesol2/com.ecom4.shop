package com.ecom4.cart.service;

import java.util.Hashtable;

import com.ecom4.order.dto.OrderDTO;

public interface CartService {

	void setCartList(Hashtable<Integer, OrderDTO> hCartList);

	Hashtable<Integer, OrderDTO> addCart(OrderDTO ovo);

	Hashtable<Integer, OrderDTO> updateCart(OrderDTO ovo);

	Hashtable<Integer, OrderDTO> deleteCart(OrderDTO ovo);

	Hashtable<Integer, OrderDTO> getCartList();

}
