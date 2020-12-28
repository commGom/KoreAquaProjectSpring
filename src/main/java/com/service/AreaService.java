package com.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AreaDAO;
import com.dto.AreaPageDTO;

@Service
public class AreaService {

	@Autowired
	AreaDAO dao; 
	
	 public AreaPageDTO Area_main(int curPage, String area) {
		 HashMap<String, String> map = new HashMap<String, String>();
		 map.put("area", area);
		 AreaPageDTO pDTO = dao.AreaMain(curPage, map);
		return pDTO;
	 }
	 
	 public AreaPageDTO Area_Initial (String TITLE, int curPage, String REGIONCD) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("check", "check");
			map.put("TITLE", TITLE ); 
			map.put("REGIONCD", REGIONCD);
		
			if(TITLE.equals("")) { 
				System.out.println("asdasdasd");
				AreaPageDTO list = dao.Area_Region(map,curPage,REGIONCD);
				return list;   
			} else {
				System.out.println("bbbbbbbbbbbbbbbbb");
				AreaPageDTO list = dao.Area_InitialSearch(map, curPage, REGIONCD);
				return list;   
			}
		 }  	  
}// end class
