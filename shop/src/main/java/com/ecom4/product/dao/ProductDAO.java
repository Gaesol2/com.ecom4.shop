package com.ecom4.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.order.dto.OrderDTO;
import com.ecom4.product.dto.ProductDTO;

@Mapper
public interface ProductDAO {

	List<ProductDTO> getProductList(PageDTO pageDto);

	int getProductTotal();

	int setProduct(ProductDTO pdto);

	ProductDTO getProduct(int p_no);

	int updateStocks(List<OrderDTO> list);

	int updateProduct(ProductDTO pdto);

	int orderCntOfProduct(int pno);

	int productDel(ProductDTO pdto);

}
