package com.ak.login.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring4.context.SpringWebContext;

import com.ak.dao.impl.SDepartService;
import com.ak.entity.SDepart;
import com.ak.entity.SUser;
import com.ak.pub.Appctx;
import com.ak.pub.TransObj;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/")
public class LoginController {

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
		return "/index";
	}


}