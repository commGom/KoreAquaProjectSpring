package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.MySqlSessionFactory;
import com.dao.JusoDAO;
import com.dto.JusoDTO;
@Service
public class JusoService {
	@Autowired
	JusoDAO dao;	

	public List<String> setSigun(String sido) {		
		List<String> sigunList = dao.setSigun(sido);
		
		return sigunList;
	}

	public List<String> setDong(String sigun) {
		List<String> dongList = dao.setDong(sigun);
		
		return dongList;
	}

	public List<JusoDTO> searchCode(Map<String, String> map) {		
		List<JusoDTO> codeList =  dao.searchCode(map);
		
		return codeList;
	}
}
