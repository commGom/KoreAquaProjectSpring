package com.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.AdminDTO;
import com.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService service;
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ModelAndView getAdminLoginPage() {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("adminLogin");
	    
	    return mav;
	}
	
	@RequestMapping(value="/admin/login", method=RequestMethod.POST)
	public String adminLogin(AdminDTO aDTO, HttpSession session, RedirectAttributes m) {
		// form에서 넘어온 aDTO로 조회
		AdminDTO admin = service.adminLogin(aDTO);
		
		String nextPage = "";
		
		if(admin != null) {
			session.setAttribute("adminLogin", admin);
			m.addFlashAttribute("mesg", "로그인 성공");
			nextPage = "qna";
		} else {
			m.addFlashAttribute("mesg", "로그인 실패. 다시 로그인하세요.");
			nextPage = "admin";
		}
		
		return "redirect:/" + nextPage;
	}
	
	@RequestMapping(value="/admin/logout", method=RequestMethod.GET)
	public String adminLogout(HttpSession session, RedirectAttributes m) {
		session.invalidate();
	    
	    m.addFlashAttribute("mesg", "로그아웃되었습니다.");
	    
	    return "redirect:/qna";
	}

}
