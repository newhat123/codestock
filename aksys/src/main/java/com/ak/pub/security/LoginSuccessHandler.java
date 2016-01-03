package com.ak.pub.security;

import java.lang.annotation.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import com.ak.entity.SUser;

/** 可以在这里将用户登录信息存入数据库。
 * 
 * 
 * */
public class LoginSuccessHandler extends org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler {
   /** @param request 
    * @param response 
    * @param authentication
    * @exception IOException
    * @exception ServletException
    * */
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {  
         
       SUser userDetails = (SUser)authentication.getPrincipal();  
         
       //输出登录提示信息  
       System.out.println("管理员 " + userDetails.getEmail() + " 登录");  
   	//System.out.println("Okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
       System.out.println("IP :"+getIpAddress(request));
             
       super.onAuthenticationSuccess(request, response, authentication);  
   }
   
   /** @param request
    * */
   public String getIpAddress(HttpServletRequest request) {    
       String ip = request.getHeader("x-forwarded-for");    
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
           ip = request.getHeader("Proxy-Client-IP");    
       }    
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
           ip = request.getHeader("WL-Proxy-Client-IP");    
       }    
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
           ip = request.getHeader("HTTP_CLIENT_IP");    
       }    
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
           ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
       }    
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
           ip = request.getRemoteAddr();    
       }    
       return ip;    
   }

}