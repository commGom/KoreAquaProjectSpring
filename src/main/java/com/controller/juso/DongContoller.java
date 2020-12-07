package com.controller.juso;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.service.JusoService;

@Controller
public class DongContoller {
	@Autowired
	JusoService service;
	
	@RequestMapping(value="/Sigun" ,method=RequestMethod.GET)
	@ResponseBody
	public List<String> Sigun(@RequestParam("sido") String sido) {
		System.out.println("시도: " + sido);
	
			List<String> sigunList = service.setSigun(sido);			
			System.out.println(sigunList.toString());		
			System.out.println(sigunList);				
			return sigunList;
		
	}
	
	@RequestMapping(value="/Dong",method=RequestMethod.GET)
	@ResponseBody
	public List<String> Dong(@RequestParam("sigun") String sigun ) {
		sigun = sigun.trim();
		System.out.println("시군: " + sigun);
		
		
			List<String> dongList = service.setDong(sigun);
			System.out.println(dongList.toString());
			System.out.println(dongList);
			return dongList;
		
		
	}
}
