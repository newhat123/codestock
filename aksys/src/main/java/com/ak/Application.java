package com.ak;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ak.dao.impl.SDepartService;
import com.ak.dao.impl.SUserService;
import com.ak.entity.SDepart;
import com.ak.entity.SUser;
import com.ak.pub.Appctx;


@SpringBootApplication
public class Application {
   
   public static void main(String[] args) {
	   SpringApplication app=new SpringApplication(Application.class);  		
  	   Appctx.ctx=app.run(args);
  	   
//		SUserService suserService = (SUserService) Appctx.ctx.getBean("suserService");
//		SUser su = suserService.findUserById(1);
//		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
//		su.setPassword(bc.encode("111"));
//		System.out.println(su.getPassword());
//		suserService.update(su);
  	   
//  	 SDepartService sd = (SDepartService) Appctx.ctx.getBean("sdepartService");
//  	 List<SDepart> l=sd.getAll();
//  	 System.out.println(l.size());
//  	 l=sd.findByFname("办公室");
//  	 System.out.println(l.size());
   }

}