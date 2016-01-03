//import springidol.dao.impl.UserService;

package com.ak.pub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.ak.entity.SUser;
import com.ak.pub.security.SecurityUser;


@Component
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
  
	
   @Autowired
   @Qualifier("puserService")
   private PUserService puserService;
   
  
   @Override
   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
   	SUser user = puserService.findUserByEmail(userName);
   	if (user == null) {
   		throw new UsernameNotFoundException("UserName " + userName + " not found");
   	}
   	
   	return new SecurityUser(user);
   }

}