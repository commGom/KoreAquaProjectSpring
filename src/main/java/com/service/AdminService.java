package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminDAO;
import com.dao.JusoDAO;
import com.dto.AdminDTO;

@Service
public class AdminService {
	@Autowired
	AdminDAO dao;
	public AdminDTO adminLogin(AdminDTO dto) {
		AdminDTO aDTO = null;
		aDTO = dao.adminLogin(dto);
		
		return aDTO;
	}
}
