package com.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.MySqlSessionFactory;
import com.dao.SubscribeDAO;
import com.dto.SubscribeDTO;

@Service
public class SubscribeService {
	@Autowired
	SubscribeDAO dao;
	
	
	public void emailAdd(SubscribeDTO dto) {
		dao.emailAdd( dto);
		
		
	}

}
