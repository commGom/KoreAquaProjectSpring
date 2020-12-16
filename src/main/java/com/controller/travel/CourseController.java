
package com.controller.travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.TravelPage;
import com.service.TravelService;

@Controller
public class CourseController  {

	@Autowired
	TravelService service;
	
	@RequestMapping(value="/CourseServlet", method = RequestMethod.GET)
	
	public ModelAndView CourseCon(@RequestParam(required=false, defaultValue="1")String curPage,
			@RequestParam("course")String course,
			@RequestParam("word")String word) {
		
		
		TravelPage pDTO = service.course(Integer.parseInt(curPage), course);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("course", pDTO);
		mav.addObject("word",word);
		mav.setViewName("Course"); 
		return mav;
 
	}  

		

}
