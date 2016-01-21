package com.ak.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ak.dao.impl.SDepartService;
import com.ak.entity.SDepart;
import com.ak.pub.Appctx;
import com.alibaba.fastjson.JSON;


//这是用来测试页面参数传递用的
//实际项目中用不到的
@Controller
@RequestMapping("/")
public class Testcontroller {
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
	
	
	
	@RequestMapping(value = "/guests/{surname}", method = RequestMethod.GET)
	public String showGuestList(Model model, @PathVariable("surname") String surname) {
		System.out.println("with param");
		SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");		
	    model.addAttribute("guests", sd.findByFname(surname));
	    return "fragments/sysfrags::resultsList";
	}

	@RequestMapping(value = "/guests", method = RequestMethod.GET)
	public String showGuestList(Model model) {		
		SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");
		List<SDepart> l=sd.getAll();
		System.out.println(l.size());
	    model.addAttribute("guests", l);
	    return "fragments/sysfrags::resultsList";
	}
	
	@RequestMapping(value = "/guests/edit")
	public String editDepart(Model model) {		
		SDepart sd=new SDepart();
		sd.setFname("编辑框用的");
	    model.addAttribute("depart", sd);
	    return "fragments/sysfrags::depEditModel";
	}
	
	@RequestMapping(value = "/guests/edited")
	public String editedDepart(Model model) {		
		SDepart sd=new SDepart();
		sd.setFname("编辑框用的");
		sd.setDid(123);
		sd.setBref("bjkyd");
		sd.setFtype(1);
	    model.addAttribute("depart", sd);
	    return "fragments/sysfrags::depEdited";
	}
	
	

}
