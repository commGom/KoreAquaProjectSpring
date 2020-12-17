
package com.controller.travel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.TravelDTO;
import com.dto.TravelPage;
import com.service.TravelService;

@Controller
public class CourseController  {

	@Autowired
	TravelService service;
	
	@RequestMapping(value="/CourseServlet", method = RequestMethod.GET)
	
	public ModelAndView CourseCon(@RequestParam(required=false, defaultValue="1")String curPage,
			@RequestParam(required=false, defaultValue="")String course) {
		
		
		TravelPage pDTO = service.course(Integer.parseInt(curPage), course);
		List<TravelDTO> list = pDTO.getList();
		int perPage = pDTO.getPerPage();
		int totalCount = pDTO.getTotalCount();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("curPage",curPage);
		mav.addObject("perPage",perPage);
		mav.addObject("totalCount",totalCount);
		mav.addObject("list", list);
		mav.addObject("course", course);
		mav.setViewName("Course"); 
		System.out.println(curPage+"\t"+perPage+"\t"+totalCount);
		return mav;
 
	}  

		

}
