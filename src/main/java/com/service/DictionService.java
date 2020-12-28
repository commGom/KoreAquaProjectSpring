package com.service;


import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.MySqlSessionFactory;
import com.dao.DictionDAO;
import com.dto.DictionPageDTO;

@Service
public class DictionService {
	
	@Autowired
	DictionDAO dao;
	
	public DictionPageDTO searchAll(int curPage, String hnm) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hnm", hnm);
		DictionPageDTO pDTO = dao.searchAll(curPage, map);   
		return pDTO;
	}  
	
	public DictionPageDTO search(String hnm, int curPage) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("check", "check");
		map.put("hnm", hnm);
		DictionPageDTO list = dao.search(map, curPage);
		return list;
	} 
	
	public DictionPageDTO initial(String initial, int curPage) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("initial", initial);
		DictionPageDTO pDTO = dao.initial(map, curPage);
		return pDTO;
	}
}
