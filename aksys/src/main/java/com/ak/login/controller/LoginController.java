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

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	/**
	 * @param request
	 * 
	 * 			@RequestMapping("/login") public String
	 *            login( @RequestParam(value = "error", required = false)
	 *            boolean error, ModelMap model) { if(error==true){
	 *            System.out.println("Error occures"); }else{
	 *            System.out.println("no Errors"); } return "login"; }
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "login";
	}

	// public String depmg(List<SDepart> departs) 这种模式是不可行的。
	// spring mvc 不能直接接收数组list参数， 必需把这个数组或list作为一个对象的属性接收， 如对象 A a;
	// 有个list属性b然后页面可以通过b[0].name、b[0].xxx、b[1].xxx传值。 控制层方法参数必需是对象a;

	@RequestMapping("/depmg")
	public String depmg(Model model, Integer edit) {

		
			if (!model.containsAttribute("departs")) {
				// trans = (TransObj) Appctx.ctx.getBean("transObj");
				SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");
				Page<SDepart> pager = sd.getListWithPaging(0, 2);
				List<SDepart> departs = pager.getContent();
				// trans.setList(departs);
				model.addAttribute("departs", departs);
				System.out.println("NOT");
			}else{
				System.out.println("YES");
			}
		
		return "depmg";
	}

	// @Autowired
	// @RequestMapping("/depmg")
	// public String depmg(@ModelAttribute("departs") TransObj departs) {
	//
	// SDepartService sd = (SDepartService)
	// Appctx.ctx.getBean("sdepartService");
	// Page<SDepart> pager = sd.getListWithPaging(0, 2);
	// departs.setList(pager.getContent());
	//
	//
	// return "depmg1";
	// }

	@RequestMapping("/orgreg")
	public String orgreg(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "orgreg";
	}

	@RequestMapping("/userreg")
	public String userreg(HttpServletRequest request) {
		// System.out.println("Login from hear!");
		return "userreg";
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

		return "hello";
	}

	@RequestMapping("/")
	public String root() {
		return "index";
	}

	/**
	 * @param name
	 * @param model
	 */
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

		return "greeting";
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
		return "user";
	}

	// @Valid @ModelAttribute("user") SUser user 将user绑定到model中的user属性后就进行验证。
	// 如果不绑定，则return getuser(model)后，model中将没有user属性。
	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "save")
	public String submitparam(Model model, @Valid @ModelAttribute("user") SUser user, BindingResult result) {
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

		return "redirect:/user";
	}
	
	   @RequestMapping(value = "/adddep", method = RequestMethod.POST)  
	   @ResponseBody  
	   public Map<String, String> addUser(@RequestBody SDepart model) {  	    
	     Map<String, String> map = new HashMap<String, String>(1);  
	     map.put("success", "true");  
	     return map;  
	   } 
	   
	   /**
		   * ajax请求不需要返回页面，只需要得到response中的数据即可，所以方法签名为void即可
		   * 
		   * @param request
		   * @param response 
		   */
		  @RequestMapping(value = "/ajax", method = RequestMethod.POST)
		  public void ajaxDatas(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("Entered");
		    String jsonResult = getJSONString(request);
		    renderData(response, jsonResult);
		    
		  }

		  private String getJSONString(HttpServletRequest request) {
		    //故意构造一个数组，使返回的数据为json数组，数据更复杂些
		    List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(5);
		    Map<String, Object> map1 = new HashMap<String, Object>(10);
		    //可以获得ajax请求中的参数
		    map1.put("a", request.getParameter("a"));
		    map1.put("b", request.getParameter("b"));
		    map1.put("c", request.getParameter("c"));
		    datas.add(map1);
		    //故意构造一个数组，使返回的数据为json数组，数据更复杂些
		    Map<String, Object> map2 = new HashMap<String, Object>(10);
		    map2.put("a", "11");
		    map2.put("b", "22");
		    map2.put("c", "33");
		    datas.add(map2);
		    String jsonResult = JSON.toJSONString(datas);
		    return jsonResult;
		  }

		  /**
		   * 通过PrintWriter将响应数据写入response，ajax可以接受到这个数据
		   * 
		   * @param response
		   * @param data 
		   */
		  private void renderData(HttpServletResponse response, String data) {
		    PrintWriter printWriter = null;
		    try {
		      printWriter = response.getWriter();
		      printWriter.print(data);
		    } catch (IOException ex) {
		      System.out.println("renderData Error");
		    } finally {
		      if (null != printWriter) {
		        printWriter.flush();
		        printWriter.close();
		      }
		    }
		  }

}