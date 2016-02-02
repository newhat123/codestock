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
@RequestMapping("/learn")
public class LearnController {

	// private SUserService tt;

	@Autowired
	private HttpServletRequest request;
	
	@ModelAttribute("CUser")
	public SUser procUser(){	
		SUser user=null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth.getPrincipal() instanceof UserDetails) {
			user = (SUser) auth.getPrincipal();				
		}		
		return user;		
	}

	@RequestMapping("/home")
	public String home() {
		return "/learn/home";
	}
	
	@RequestMapping("/orgreg")
	public String orgreg(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "/learn/orgreg";
	}

	@RequestMapping("/userreg")
	public String userreg(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "/learn/userreg";
	}

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("Come in here!");
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth.getPrincipal() instanceof UserDetails) {
			SUser user = (SUser) auth.getPrincipal();
			System.out.println(user.getEmail());
		}
		return "/learn/hello";
	}
	
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("sex", "男");

		SUser user = new SUser();
		model.addAttribute("user", user);

		List<SUser> users = new ArrayList<SUser>();
		users.add(new SUser());
		users.add(new SUser());
		users.add(new SUser());
		users.add(new SUser());
		users.add(new SUser());
		model.addAttribute("users", users);

		return "/learn/greeting";
	}
	
	// 每次都返回一个新的User
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getuser(Model model) {

		if (!model.containsAttribute("user")) {
			// System.out.println("Not has user properties");
			SUser user = new SUser();
			model.addAttribute("user", user);
			user.setFname("start now");
			user.setPassword("can not tell you");
			user.setEmail("testemail");
		}
		return "/learn/user";
	}

	// @Valid @ModelAttribute("user") SUser user 将user绑定到model中的user属性后就进行验证。
	// 如果不绑定，则return getuser(model)后，model中将没有user属性。
	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "save")
	public String submitparam(Model model, @Valid @ModelAttribute("user") SUser user, BindingResult result) {
		System.out.println("hhhhh");
		// if (result.hasErrors()){
		// List<ObjectError> errorList = result.getAllErrors();
		// for(ObjectError error : errorList){
		// System.out.println(error.getDefaultMessage());
		// }
		// System.out.println("go to getuser");
		// return getuser(model);
		// }
		return getuser(model);		
	}

	// 此处加入了上传文件的测试。user.html中需指定enctype="multipart/form-data"
	// 并使用<input type="file" name="shangchuan" />来上传文件。
	// input的name可以直接用来在这里作为变量。
	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "addrow")
	public String submitparam1(MultipartFile shangchuan, SUser user) {

		System.out.println("entered!");
		if (!shangchuan.isEmpty()) {
			System.out.println("not null!");
			String name = shangchuan.getOriginalFilename();
			try {
				byte[] bytes = shangchuan.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				System.out.println("Upload successed!");
			} catch (Exception e) {
			}
		} else {
		}

		System.out.println("username is :" + user.getFname());

		return "redirect:/learn/user";
	}
}