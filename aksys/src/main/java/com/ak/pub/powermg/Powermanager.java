package com.ak.pub.powermg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.dao.impl.ILoginfoService;
import com.ak.dao.impl.SUserService;
import com.ak.entity.SModules;

/**
 * 保存权限  记录登录信息 不说了,asfdsa
 * @author Administrator
 * @version 1.0
 * @updated 30-1月-2016 11:15:41
 */
 @Service("powermanager")
public class Powermanager {
	
	@Autowired
	private ILoginfoService m_ILoginfoService;
	
	
	@Autowired
	private SUserService suserService;
	
	
	/*
	 * 以user的id为键，存储用户权限列表
	 */
	private Map<Integer,List<SModules>> userpowers=new HashMap<Integer,List<SModules>>();
	
	/*
	 * 根据SUser的Did获取其权限列表
	 */
	public List<SModules> fetchPwlist(Integer did){
		List<SModules> list=null;
		if(userpowers.containsKey(did)){
			list=userpowers.get(did);
		}else{
			list=new ArrayList<SModules>();
			SModules sm=new  SModules();
			sm.setDid(did);
			list.add(sm);
		}
		
		return list;
	}
	

	/**
	 * 权限管理，设定可见项目、记录登录信息
	 */
	public Powermanager(){

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