package com.service;

import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.MySqlSessionFactory;
import com.dao.AreaDAO;
import com.dao.DictionDAO;
import com.dto.AreaPage;

@Service
public class AreaService {

	@Autowired
	AreaDAO dao; 
	
	 public AreaPage area_main(int curPage, String area) {
		 HashMap<String, String> map = new HashMap<String, String>();
		 map.put("area", area);
		 AreaPage pDTO = dao.AreaMain(curPage, map);
		return pDTO;
	 }
	 
	 public AreaPage AInitial (String TITLE, int curPage, String REGIONCD) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("check", "check");
			map.put("TITLE", TITLE ); 
			map.put("REGIONCD", REGIONCD);
		
			if(TITLE.equals("")) { 
				System.out.println("asdasdasd");
				AreaPage list = dao.ARegion(map,curPage,REGIONCD);
				return list;   
			} else {
				System.out.println("bbbbbbbbbbbbbbbbb");
				AreaPage list = dao.ATitle(map, curPage, REGIONCD);
				return list;   
			}
		 }  	  
}// end class
