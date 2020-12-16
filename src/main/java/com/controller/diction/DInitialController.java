package com.controller.diction;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.DictionDTO;
import com.dto.PageDTO;
import com.service.DictionService;


@Controller
public class DInitialController {
	
	@Autowired
	DictionService service; 
	
	@RequestMapping(value="/DInitial", method = RequestMethod.GET)

	public ModelAndView DictionServlet(@RequestParam("searchValue") String hnm,
			@RequestParam(required=false, defaultValue="1") String curPage) {
	
		PageDTO pDTO = service.search(hnm, Integer.parseInt(curPage));
		List<DictionDTO> list = pDTO.getList();
		int perPage = pDTO.getPerPage();
		int totalCount = pDTO.getTotalCount();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchValue",hnm);
		mav.addObject("perPage",perPage);
		mav.addObject("totalCount",totalCount);
		mav.addObject("curPage",curPage);
		mav.addObject("list", list);
		mav.addObject("test", "test");
		mav.setViewName("Initial");
		return mav; 
		
	}
}


