package com.examsys.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/logout")
public class LogoutAtion {

	
	@RequestMapping("/out")
	public ModelAndView logout(HttpServletRequest req ){
		req.getSession().invalidate();
		SecurityUtils.getSubject().logout();
		//SecurityUtils.getSubject().getSession().setTimeout(0);
		ModelAndView mv=new ModelAndView();
		
		
		mv.setViewName("redirect:/index.jsp");
		
		
		return mv;
	}
	
}
