//import springidol.dao.impl.UserService;

package com.ak.pub.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ak.entity.SModules;
import com.ak.entity.SUser;
import com.ak.pub.powermg.Powermanager;
import com.ak.pub.security.SecurityUser;


@Component
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
  
	
   @Autowired
   @Qualifier("puserService")
   private PUserService puserService;
   
   @Autowired
   private Powermanager powermanager;
   
  
   @Override
   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
   	SUser user = puserService.findUserByEmail(userName);
   	if (user == null) {
   		throw new UsernameNotFoundException("UserName " + userName + " not found");
   	}
   	//System.out.println("Executed here!");
   	List<SModules> l=powermanager.fetchPwlist(user.getDid());
   	System.out.println("Size is:"+l.size());
   	
   	return new SecurityUser(user);
   }

}