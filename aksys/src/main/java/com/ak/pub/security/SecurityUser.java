package com.ak.pub.security;

import com.ak.entity.SUser;
import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ak.entity.SRole;

 
public class SecurityUser extends com.ak.entity.SUser implements org.springframework.security.core.userdetails.UserDetails {
    
   private static final long serialVersionUID = 1L;
   
   /** @param suser
    * */
   public SecurityUser(SUser suser) {
   	if(suser != null)
   	{
   		this.setDid(suser.getDid());
   		this.setFname(suser.getFname());
   		this.setEmail(suser.getEmail());
   		this.setPassword(suser.getPassword());
   		this.setDob(suser.getDob());
   		this.setSRoles(suser.getSRoles());
   	}		
   }
   
    
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
   	
   	Collection<GrantedAuthority> authorities = new ArrayList<>();
   	Set<SRole> userRoles = this.getSRoles();
   	
   	if(userRoles != null)
   	{
   		for (SRole role : userRoles) {
   			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getFname());
   			authorities.add(authority);
   			//System.out.println("get a role!"+role.getFname());
   		}
   	}
   	return authorities;
   }
   
    
   @Override
   public String getPassword() {
   	return super.getPassword();
   }
   
    
   @Override
   public String getUsername() {
   	return super.getEmail();
   }
   
    
   @Override
   public boolean isAccountNonExpired() {
   	return true;
   }
   
    
   @Override
   public boolean isAccountNonLocked() {
   	return true;
   }
   
    
   @Override
   public boolean isCredentialsNonExpired() {
   	return true;
   }
   
    
   @Override
   public boolean isEnabled() {
   	return true;
   }

}