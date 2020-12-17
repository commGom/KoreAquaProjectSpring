package com.controller.main;

import org.springframework.web.bind.annotation.RequestMapping;

public class mainController {
	//각자 페이지 컨트롤러에 가져가서 사용하세요~
	@RequestMapping("/")
	public String mainPage() {
		return "index";
	}
	
	@RequestMapping("/Travel_course")
	public String Travel_course() {
		return "Travel";
	}
	
	@RequestMapping("/Travel_area")
	public String Travel_area() {
		return "Travel_area";
	}
	
	@RequestMapping("/Dictionary")
	public String Dictionary() { 
		return "Diction"; 
	}
}
