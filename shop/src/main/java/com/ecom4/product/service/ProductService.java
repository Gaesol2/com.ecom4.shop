package com.ecom4.product.service;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.order.dto.OrderDTO;
import com.ecom4.product.dto.ProductDTO;

public interface ProductService {

	Map<String, Object> getProductList(PageDTO pageDto);

	int setProduct(ProductDTO pdto, MultipartFile file);

	int updateProduct(ProductDTO pdto, MultipartFile file);

	ProductDTO getProduct(int p_no);

	void updateStocks(Hashtable<Integer, OrderDTO> hCartList);

	int orderCntOfProduct(int pno);

	int productDel(ProductDTO pdto);


}
