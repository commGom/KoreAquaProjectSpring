package com.service;


import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.MySqlSessionFactory;
import com.dao.DictionDAO;
import com.dto.PageDTO;

@Service
public class DictionService {
	
	@Autowired
	DictionDAO dao;
	
	public PageDTO searchAll(int curPage, String hnm) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hnm", hnm);
		PageDTO pDTO = dao.searchAll(curPage, map);   
		return pDTO;
	}  
	
	public PageDTO search(String hnm, int curPage) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("check", "check");
		map.put("hnm", hnm);
		PageDTO list = dao.search(map, curPage);
		return list;
	} 
	
	public PageDTO initial(String initial, int curPage) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("initial", initial);
		PageDTO pDTO = dao.initial(map, curPage);
		return pDTO;
	}
}
