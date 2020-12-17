package com.controller.MSW;
//감사합니다 갓윤!!
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.MSWInfoService;
//
@Controller
public class LocController {
	@Autowired
	MSWInfoService service;
	
	@RequestMapping(value="/siguList" ,method=RequestMethod.GET)
	@ResponseBody
	public List<String> Sigun(@RequestParam("doNm") String doNm) {
		System.out.println("시도: " + doNm);
	
			List<String> siguList = service.setSigu(doNm);				
			System.out.println(siguList);				
			return siguList;
		
	}
	
	@RequestMapping(value="/mnrlspNmList",method=RequestMethod.GET)
	@ResponseBody
	public List<String> mnrlspNmList(@RequestParam("siGuNm") String siGuNm ) {
		System.out.println("군: " + siGuNm);
		
		
			List<String> mnrlspNmList = service.setMnrlspNm(siGuNm);
			System.out.println(mnrlspNmList);
			return mnrlspNmList;
		
		
	}
}
