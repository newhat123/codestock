package com.ak.login.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {

	// private SUserService tt;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "/login";
	}
	
	@RequestMapping("/")
	public String root() {
		return "/pub/index";
	}
	
}