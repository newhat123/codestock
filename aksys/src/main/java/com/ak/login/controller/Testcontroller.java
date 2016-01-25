package com.ak.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ak.dao.impl.ISellistService;
import com.ak.dao.impl.SDepartService;
import com.ak.entity.SDepart;
import com.ak.entity.SUser;
import com.ak.pub.Appctx;
import com.alibaba.fastjson.JSON;

//这是用来测试页面参数传递用的
//实际项目中用不到的
@Controller
@RequestMapping("/")
public class Testcontroller {
	
	@Autowired
	private HttpServletRequest request;
	
	/*
	 * 接收参数getParameter()的时候:
	 * 如果地址栏/springmvc/hello.htm上面没有传递参数,那么当id为int型的时候会报错,当id为Integer的时候值为null
	 * 当地址栏为/springmvc/hello.htm?id=10的时候,action中有三种接收方式 1、String
	 * hello(@RequestParam(value = "userid") int
	 * id),这样会把地址栏参数名为userid的值赋给参数id,如果用地址栏上的参数名为id,则接收不到 2、String
	 * hello(@RequestParam int id),这种情况下默认会把id作为参数名来进行接收赋值 3、String hello(int
	 * id),这种情况下也会默认把id作为参数名来进行接收赋值
	 * 注:如果参数前面加上@RequestParam注解,如果地址栏上面没有加上该注解的参数,例如:id,那么会报404错误,找不到该路径
	 */
	@Autowired
	private ISellistService isellistService;
	
	@RequestMapping(value = "/helloa")
	public String helloa(int id) {// getParameter()的方式
		System.out.println("hello action:" + id);
		return "helloa";
	}

	// 返回页面参数的第一种方式,在形参中放入一个map
	@RequestMapping(value = "/hello1")
	public String hello1(int id, Map<String, Object> map) {
		System.out.println("hello1 action:" + id);
		map.put("name", "huangjie");
		return "helloa";
	}

	// 返回页面参数的第二种方式,在形参中放入一个Model
	@RequestMapping(value = "/hello2")
	public String hello2(int id, Model model) {
		System.out.println("hello2 action:" + id);
		model.addAttribute("name", "huangjie");
		// 这个只有值没有键的情况下,使用Object的类型作为key,String-->string
		model.addAttribute("ok");
		return "helloa";
	}

	// 得到request,response,session等,只要在方法形参中声明参数即可
	@RequestMapping(value = "/hello3")
	public String hello3(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("hello3 action:" + id);
		return "helloa";
	}

	/* 这是和userreg.html配合，形成搜索后部分刷新页面的Ajax功能的代码
	 * 使用了sysfrags.html中的th:fragment="resultsList
	 */
	@RequestMapping(value = "/guests/{surname}", method = RequestMethod.GET)
	public String showGuestList(Model model, @PathVariable("surname") String surname) {
		System.out.println("with param");
		SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");
		model.addAttribute("guests", sd.findByFname(surname));
		return "fragments/sysfrags::resultsList";
	}
	
/*	
	和sysfrags的resultsList depEdited以及后台的
	@RequestMapping("/depgl") 
	@RequestMapping(value = "/guests")
	@RequestMapping(value = "/guests/edited/{did}/{fname}/{ftype}/{bref}")
	公共构成完整的Ajax编辑功能*/
	
	@RequestMapping("/depgl")
	public String depgl(Model model) {
		
		SUser user=new SUser();
		user.setFname("ttttttt");
		model.addAttribute("user", user);
		
		model.addAttribute("isellists", isellistService.getDepTypeList());
		return "depgl";
	}
	
	
	/*@RequestMapping("/depmg")
	 *@RequestMapping(value = "/guests")
	 *@RequestMapping(value = "/guests/edit/{did}")
	 *@RequestMapping(value = "/guests/edited/{did}/{fname}/{ftype}/{bref}")
	 *depmg.html
	 *fragments/sysfrags.html中的
	 *th:fragment="resultsList"  th:fragment="depEdited"  th:fragment="depEditModel"
	 *是一整套的Ajax解决方案，不要删除 其中的部分内容    */
	
	
	// public String depmg(List<SDepart> departs) 这种模式是不可行的。
	// 只能加入Model中传递。
	@RequestMapping("/depmg")
	public String depmg() {
		return "depmg";
	}

	@RequestMapping(value = "/guests")
	public String showGuestList(Model model) {
		SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");
		List<SDepart> l = sd.getAll();
		//System.out.println(l.size());
		model.addAttribute("sdeparts", l);
		return "fragments/sysfrags::resultsList";
	}
	

	@RequestMapping(value = "/guests/edit/{did}")
	public String editDepart(SDepart depart, Model model) {
		SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");
		// 无法在前台通过sDepart获取对象，必须加入model
		// 此处路径参数被直接赋值到depart
		// 如果用@ModelAttribute("sDepart") SDepart depart绑定，则对depart的修改都不能返回到前台
		// 前台始终得到用路径参数赋值的depart
		// 加入model则可以返回任意值。
		model.addAttribute("sDepart", sd.getById(depart.getDid()));
		return "fragments/sysfrags::depEditModel";
	}
	
	//@RequestMapping(value = "/guests/edited/{did,fname,ftype,bref}")
	//@RequestMapping(value = "/guests/edited/{did}/{fname}/{ftype}/{bref}")
	//public String editedDepart(SDepart depart, Model model) {	
	@RequestMapping(value = "/guests/edited")
	/*public String editedDepart(@RequestParam Integer did,@RequestParam String fname,
			@RequestParam String ftype,@RequestParam String bref,Model model) {*/
	public String editedDepart(SDepart depart,Model model) {
		
		SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");
		SDepart sdd = sd.getById(depart.getDid());

		sdd.setFname(depart.getFname());
		sdd.setFtype(depart.getFtype());
		sdd.setBref(depart.getBref());
		sd.saveSDepart(sdd);
		// 无法在前台通过sDepart获取对象，必须加入model
		// 此处路径参数被直接赋值到depart
		// 如果用@ModelAttribute("sDepart") SDepart depart绑定，则对depart的修改都不能返回到前台
		// 前台始终得到用路径参数赋值的depart
		// 加入model则可以返回任意值。
		model.addAttribute("sDepart", sdd);		
		//System.out.println(depart.getFname());
		return "fragments/sysfrags::depEdited";
	}

}
