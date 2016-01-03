/***********************************************************************
 * Module:  Application.java
 * Author:  Administrator
 * Purpose: Defines the Class Application
 ***********************************************************************/

package com.ak.pub;

import java.lang.annotation.*;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ak.pub.Appctx;

 
@SpringBootApplication
public class Application {
   /** @param args
    * */
   @SuppressWarnings("unchecked")
   public static void main(String[] args) {
   		//Appctx.ctx = SpringApplication.run(Application.class, args);
   		
   		SpringApplication app=new SpringApplication(Application.class);
   		
   		//app.addListeners(new ApplicationListener<HttpSessionEventPublisher>());
   		
   		Appctx.ctx=app.run(args);
   		
   
   		// System.setProperty("http.proxyHost", "localhost");
   		// System.setProperty("http.proxyPort", "8888");
   		// System.setProperty("https.proxyHost", "localhost");
   		// System.setProperty("https.proxyPort", "8888");
   
   		// System.out.println("this is run able!");
   
   		// UserService
   		// userService=(UserService)Appctx.ctx.getBean("userService");
   		// userService.get(1);
   
   		// Thinker performer=(Thinker)Appctx.ctx.getBean("volunteer");
   		// performer.thinkOfSomething("aaaaa");
   
   		/*
   		 * 这是关于数据库的全面测试。 动态创建数据源dataSource3 分别使用3个数据源进行选择测试 分别使用3个数据源进行事务性删除测试。
   		 * setDefaultTargetDataSource(dataSource1())指定默认数据源 DynamicDataSource
   		 * dy= (DynamicDataSource)Appctx.ctx.getBean("dataSource");
   		 * DriverManagerDataSource dm=
   		 * dy.createDataSource("com.mysql.jdbc.Driver",
   		 * "jdbc:mysql://localhost:3306/dynamic", "root", "wsdgz");
   		 * 
   		 * dy.addTargetDataSource("dataSource3", dm);
   		 * 
   		 * TUserService tuserService = (TUserService) Appctx.ctx
   		 * .getBean("tuserService");
   		 * 
   		 * DataSourceSwitcher.setDataSource("dataSource3");
   		 * 
   		 * List<TUser> tu = tuserService.findByUsername("777");
   		 * System.out.println(tu.get(0).getPassword());
   		 * 
   		 * tuserService.removeByUsername("kkk");
   		 * 
   		 * DataSourceSwitcher.setDataSource("dataSource2"); tu =
   		 * tuserService.findByUsername("777");
   		 * System.out.println(tu.get(0).getPassword());
   		 * tuserService.removeByUsername("666");
   		 * 
   		 * DataSourceSwitcher.setDataSource("dataSource1"); tu =
   		 * tuserService.findByUsername("777");
   		 * System.out.println(tu.get(0).getPassword());
   		 * 
   		 * tuserService.removeByUsername("666");
   		 */
   
   
   
   		// RestTemplate restTemplate = new RestTemplate();
   
   		/*
   		 * 使用RestFull获取List的测试
   		 * 
   		 * //List<TaskList>
   		 * lst=(List)restTemplate.getForObject("http://localhost:8080/tasks"
   		 * ,List.class);
   		 * 
   		 * TaskList[]
   		 * lst=restTemplate.getForObject("http://localhost:8080/tasks"
   		 * ,TaskList[].class);
   		 * 
   		 * 
   		 * for(TaskList tl:lst){ System.out.println(tl.getTaskDescription()); }
   		 */
   
   
   
   		/*
   		 * 使用路径参数、用GET方法去获取服务 TaskList task =
   		 * restTemplate.getForObject("http://localhost:8080/tasks/{Id}/{pd}",
   		 * TaskList.class,"2","3");
   		 * System.out.println(task.getTaskDescription());
   		 */
   
   
   
   		/*
   		 * 使用Post方法提交数据 TaskList[] lst = restTemplate .postForObject(
   		 * "http://localhost:8080/tasks/insert/{taskName}/{taskDesc}/{taskPriority}/{taskStatus}"
   		 * , null,TaskList[].class,"new taskName","new life","HIGH","DEACTIVE");
   		 */
   
   
   
   		// 这是删除的测试，删除方法没有返回值
   		// restTemplate.delete("http://localhost:8080/tasks/{id}","6");
   
   		// TaskList tlst=new TaskList();
   		// tlst.setTaskId(3);
   		// tlst.setTaskDescription("aaaaaaa");
   		// restTemplate
   		// .postForObject("http://localhost:8080/tasks/postentity",tlst,String.class);
   //将密码加密
   //		SUserService suserService = (SUserService) Appctx.ctx.getBean("suserService");
   //        SUser su= suserService.findUserById(1);
   //        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);
   //        su.setPassword(bc.encode("111111"));
   //        System.out.println(su.getPassword());
   //        suserService.update(su);
   		
   //		 Object o=  Appctx.ctx.getBean("authenticationManager");
   //		 
   //		 if(o==null){
   //			 System.out.println("is null");} else {System.out.println("not null");}
   	}

}