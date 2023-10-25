package com.ecom4.product.service;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.order.dto.OrderDTO;
import com.ecom4.product.dao.ProductDAO;
import com.ecom4.product.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDAO productDao;

	@Override
	public Map<String, Object> getProductList(PageDTO pageDto) {
		int cnt = productDao.getProductTotal();
		
		Map<String, Object> reSet = new HashMap<String, Object>();
		
		List<ProductDTO> Products = productDao.getProductList(pageDto);
		
		reSet.put("Products", Products);
		reSet.put("pcnt", cnt);
		
		return reSet;
	}


	@Override
	public int setProduct(ProductDTO pdto, MultipartFile file) {
		String sourceFileName = file.getOriginalFilename();
		File destinationFile;
		if(sourceFileName == null || sourceFileName.length() == 0) {
			pdto.setImage("ready.png");
		} else {
			pdto.setImage(sourceFileName);
			destinationFile = new File(pdto.getPath()+sourceFileName);
			destinationFile.getParentFile().mkdirs(); //파일명으로 생성
			try {
				file.transferTo(destinationFile);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("=====");
			}
		}
		return productDao.setProduct(pdto);
	}

	@Override
	public int updateProduct(ProductDTO pdto, MultipartFile file) {
		String sourceFileName = file.getOriginalFilename();
		File destinationFile;
		logger.info(sourceFileName);
		if(sourceFileName == null || sourceFileName.length() == 0) {
			if(sourceFileName==null || sourceFileName.length()==0) {
				if(pdto.getImage()==null) {
					pdto.setImage("ready.png");
				}
			}
		} else {
			pdto.setImage(sourceFileName);
			destinationFile = new File(pdto.getPath()+sourceFileName);
			destinationFile.getParentFile().mkdirs(); //파일명으로 생성
			try {
				file.transferTo(destinationFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("---------------------->");

		System.out.println(pdto);
		return productDao.updateProduct(pdto);
	}

	@Override
	public ProductDTO getProduct(int p_no) {
		
		return productDao.getProduct(p_no);
	}


	@Override
	public void updateStocks(Hashtable<Integer, OrderDTO> hCartList) {
		Set<Integer> keys = hCartList.keySet();
		List<OrderDTO> list = new ArrayList<>(keys.size());
		Iterator<Integer> iterKeys = keys.iterator();
		while(iterKeys.hasNext()) {
			list.add(hCartList.get(iterKeys.next()));
		}
		productDao.updateStocks(list);
		
	}


	@Override
	public int orderCntOfProduct(int pno) {
		return productDao.orderCntOfProduct(pno);
	}


	@Override
	public int productDel(ProductDTO pdto) {
		return productDao.productDel(pdto);
	}




}
