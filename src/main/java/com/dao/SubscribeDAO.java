package com.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.SubscribeDTO;

@Repository
public class SubscribeDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public void emailAdd(SubscribeDTO dto) {
		int n = template.insert("SubscribeMapper.emailAdd", dto);
		
	}

}
