package com.controller.qna;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.QnaDTO;
import com.service.QnaService;

@Controller
public class PasswordCheckController {
	@Autowired
	QnaService service;

	@RequestMapping(value = "/qna/pw-check", method = RequestMethod.POST)
	@ResponseBody
	public String passwdCheck(@RequestParam("passwd") String passwd, @RequestParam("num") int num) {
		System.out.println("비밀번호 체크 === " + passwd + ", "+ num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("passwd", passwd);
		map.put("num", num);
		
		QnaDTO qDTO = service.passwdCheck(map);
		
		// ajax 비동기로 jsp에 string 전달
		if(qDTO != null) {
			return "match";
		} else {
			return "mismatch";
		}
	}
}
