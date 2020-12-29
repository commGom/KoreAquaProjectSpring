
package com.controller.travel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CourseDTO;
import com.dto.CoursePageDTO;
import com.service.TravelService;

@Controller
public class CourseSelectedController  {

	@Autowired
	TravelService service;
	
	@RequestMapping(value="/Course_Selected", method = RequestMethod.GET)
	
	public ModelAndView CourseCon(@RequestParam(required=false, defaultValue="1")String curPage,
			@RequestParam(required=false, defaultValue="")String course) {
		
		
		CoursePageDTO pDTO = service.Course_SelectedArea(Integer.parseInt(curPage), course);
		System.out.println("curPage:"+curPage);
		List<CourseDTO> list = pDTO.getList();
		int perPage = pDTO.getPerPage();
		int totalCount = pDTO.getTotalCount();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("curPage",curPage);
		mav.addObject("perPage",perPage);
		mav.addObject("totalCount",totalCount);
		mav.addObject("list", list);
		mav.addObject("course", course);
		mav.setViewName("Course_Selected"); 
		return mav;
 
	}  		

}
