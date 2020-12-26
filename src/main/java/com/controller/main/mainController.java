package com.controller.main;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dto.PageQnaDTO;
import com.dto.QnaDTO;
import com.service.QnaService;

@Controller
public class mainController {
	
	@Autowired
	QnaService service;
	
	@RequestMapping("/")
	public ModelAndView mainPage(HttpServletRequest request) {
		
		int curPage = 1; // 기본 페이지 = 1
		
		// 지정된 페이지가 있는 경우 curPage 변경
		if(request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		// 검색 종류, 검색어 가져오기
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		
		PageQnaDTO pDTO = new PageQnaDTO();
		
		if(searchValue != null) { // 검색어가 있을 경우
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("searchName" , searchName);
			map.put("searchValue" , searchValue);
			pDTO = service.search(curPage, map);
		} else {
			pDTO = service.page(curPage);
		}
		
		// pDTO와 검색어 저장
		ModelAndView mav = new ModelAndView();
		List<QnaDTO> list = pDTO.getList();
		
		mav.addObject("page", pDTO);
		mav.addObject("list", list);
		mav.addObject("searchName", searchName);
		mav.addObject("searchValue", searchValue);
		mav.setViewName("index");
		
		return mav;
	}
	
	
	
	
	
}
