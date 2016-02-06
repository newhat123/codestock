package com.ak;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ak.dao.impl.SModulesService;
import com.ak.entity.SModules;
import com.ak.entity.SUser;
import com.ak.pub.Appctx;
import com.ak.pub.security.PUserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		Appctx.ctx = app.run(args);

		// SUserService suserService = (SUserService)
		// Appctx.ctx.getBean("suserService");
		// SUser su = suserService.findUserById(1);
		// BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
		// su.setPassword(bc.encode("111"));
		// System.out.println(su.getPassword());
		// suserService.update(su);

		// SDepartService sd = (SDepartService)
		// Appctx.ctx.getBean("sdepartService");
		// List<SDepart> l=sd.getAll();
		// System.out.println(l.size());
		// l=sd.findByFname("办公室");
		// System.out.println(l.size());

		// ISellistService
		// is=(ISellistService)Appctx.ctx.getBean("isellistService");
		// System.out.println(is.getSexList().size());

		// PUserService pu = (PUserService) Appctx.ctx.getBean("puserService");
		// SUser su = pu.test("admin");
		// System.out.println("user name is :" + su.getFname());

		/*SModulesService sm = (SModulesService) Appctx.ctx.getBean("smodulesService");
		List<SModules> l = sm.powerList(1);

		for(SModules s:l){
			System.out.println(s.getFcode()+"--->"+s.getFname());
		}*/
	}

}