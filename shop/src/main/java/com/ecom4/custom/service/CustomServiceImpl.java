package com.ecom4.custom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom4.custom.dao.CustomDAO;

@Service
public class CustomServiceImpl implements CustomService {

	@Autowired
	CustomDAO customDao;
	
	@Override
	public int test() {
		return customDao.test();
	}

}
