package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AdminDTO;

@Repository
public class AdminDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public AdminDTO adminLogin(AdminDTO dto) {
		AdminDTO aDTO = null;
		aDTO = template.selectOne("AdminMapper.adminLogin", dto);
		return aDTO;
	}
}
