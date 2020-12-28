package com.controller.travel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.AreaDTO;
import com.dto.AreaPage;
import com.dto.CourseDTO;
import com.service.AreaService;


@Controller
public class AreaController {
	 
	@Autowired
	AreaService service;
	
	@RequestMapping(value="/Area", method = RequestMethod.GET)
	public ModelAndView AreaCon (@RequestParam(required=false, defaultValue="1") String curPage) {
		
		
		AreaPage aDTO = service.Area_main(Integer.parseInt(curPage), "area"); 
		List<AreaDTO> list = aDTO.getList();
		System.out.println(list);
		int perPage = aDTO.getPerPage();
		int totalCount = aDTO.getTotalCount();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pdto", aDTO);  
		mav.addObject("curPage",curPage);
		mav.addObject("perPage",perPage);
		mav.addObject("totalCount",totalCount);
		mav.addObject("list", list);
		mav.setViewName("Area");
		return mav;
	}

}
 