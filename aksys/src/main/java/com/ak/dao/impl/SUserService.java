package com.ak.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.dao.SUserRepository;
import com.ak.entity.SModules;
import com.ak.entity.SUser;

/**
 * @date 2015-07-31
 * @author <a href="mailto:sosowego@126.com">hanyouqing</a>
 * @version 1.0
 * @created 30-1月-2016 11:15:42
 */
@Service("suserService")
public class SUserService {

	@Autowired
	private SUserRepository suserRepository;
	
	public SUserService(){

	}
	


	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param user    user
	 */
	public SUser create(SUser user){
		return null;
	}

	/**
	 * 
	 * @param id    id
	 */
	public void deleteUser(int id){

	}

	public List<SUser> findAll(){
		return null;
	}

	/**
	 * 
	 * @param email    email
	 */
	public SUser findUserByEmail(String email){
		return null;
	}

	/**
	 * 
	 * @param id    id
	 */
	public SUser findUserById(int id){
		return null;
	}

	/**
	 * 
	 * @param email
	 * @param password    password
	 */
	public SUser login(String email, String password){
		return null;
	}

	/**
	 * 
	 * @param user    user
	 */
	public SUser update(SUser user){
		return null;
	}

}