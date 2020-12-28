package com.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TravelDAO;
import com.dto.CoursePageDTO;
@Service
public class TravelService {

	@Autowired
	 TravelDAO dao; 
	
	 public CoursePageDTO Course_Main (int curPage, String regioncd){
			HashMap<String, String> map = new HashMap<String, String>(); 
			map.put("regioncd", regioncd);
			CoursePageDTO pDTO = dao.Course_Main(curPage, map);
			return pDTO; 
		}//end main
	 
	 public CoursePageDTO Course_SelectedArea (int curPage, String course){
		 HashMap<String, String> map = new HashMap<String, String>(); 
			map.put("course", course);
			CoursePageDTO pDTO = dao.Course_SelectedArea(curPage, map);
			return pDTO;
		}//end course
	 	
	
}// end class
