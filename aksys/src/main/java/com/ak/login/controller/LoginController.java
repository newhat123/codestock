package com.ak.login.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pub")
public class LoginController {

	// private SUserService tt;

	@Autowired
	private HttpServletRequest request;
	

	
	@RequestMapping("/orgreg")
	public String orgreg(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "/pub/orgreg";
	}

	@RequestMapping("/userreg")
	public String userreg(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "/pub/userreg";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "/pub/home";
	}


}