package com.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.MySqlSessionFactory;
import com.dao.AreaDAO;
import com.dao.TravelDAO;
import com.dto.AreaPage;
import com.dto.TravelPage;
@Service
public class TravelService {

	@Autowired
	 TravelDAO dao; 
	
	 public TravelPage main (int curPage, String regioncd){
			HashMap<String, String> map = new HashMap<String, String>(); 
			map.put("regioncd", regioncd);
			TravelPage pDTO = dao.main(curPage, map);
			return pDTO; 
		}//end main
	 
	 public TravelPage course (int curPage, String course){
		 HashMap<String, String> map = new HashMap<String, String>(); 
			map.put("course", course);
			TravelPage pDTO = dao.course(curPage, map);
			return pDTO;
		}//end course
	 	
	
}// end class
