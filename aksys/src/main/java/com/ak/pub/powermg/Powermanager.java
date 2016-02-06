package com.ak.pub.powermg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ak.dao.impl.ILoginfoService;
import com.ak.dao.impl.SModulesService;
import com.ak.dao.impl.SRoleService;
import com.ak.dao.impl.SUserService;
import com.ak.entity.SModules;
import com.ak.entity.SRole;
import com.ak.entity.SUser;

/**
 * 保存权限  记录登录信息 不说了,asfdsa
 * @author Administrator
 * @version 1.0
 * @updated 30-1月-2016 11:15:41
 */
@Service("powermanager")
public class Powermanager {
	
	@Autowired
	private SModulesService smoduleService;
	
	
	/*
	 * 以user的id为键，存储用户权限列表
	 */
	//private Map<Integer,List<SModules>> userpowers=new HashMap<Integer,List<SModules>>();
	
	//提供用户未登录的权限列表。
	private List<SModules> sysPowerList=null;	
	/**
	 * 用户未登录时，获取可用菜单。
	 * @return
	 */
	public List<SModules> getSysPowerList() {
		if(null==sysPowerList) sysPowerList=smoduleService.sysPowerList();
		return sysPowerList;
	}




	/*
	 * 根据SUser的Did获取其权限列表
	 */
//	public List<SModules> fetchPwlist(Integer userid){
//		List<SModules> list=null;
//		if(userpowers.containsKey(userid)){
//			list=userpowers.get(userid);
//		}else{
//			userpowers.put(userid, smoduleService.powerList(userid));			
//		}		
//		return list;
//	}
//	
	
	

	/**
	 * 权限管理，设定可见项目、记录登录信息
	 */
	public Powermanager(){

	}
	
	public Boolean isLogged(){
		//System.out.println("Entered heare");
		Boolean r=false;
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (null != auth && auth.getPrincipal() instanceof UserDetails) {
			//SUser user = (SUser) auth.getPrincipal();
			//System.out.println(auth.getPrincipal().getClass());
			r=true;
		}
//		} else {
//			System.out.println("NOT authed");
//		}
		return r;
	}

	/**
	 * asdfasdfsf
	 * @exception Throwable
	 */
	public void finalize() throws Throwable {

	}

	/**
	 * 记录登录信息
	 */
	public void logRec(){

	}

	/**
	 * 设定可以使用的项目
	 */
	public void avlbFunc(){

	}

}