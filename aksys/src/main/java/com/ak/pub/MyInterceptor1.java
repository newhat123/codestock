package com.ak.pub;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ak.entity.SModules;
import com.ak.entity.SUser;
import com.ak.pub.powermg.Powermanager;
import com.ak.pub.security.SecurityUser;

public class MyInterceptor1 implements HandlerInterceptor {
	
//	@Autowired
//	private Powermanager powermanager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
		// System.out.println(request.get.getRemoteUser());
		
		//if(null==powermanager) System.out.println("Null");else System.out.println("NOt null");
		//System.out.println(powermanager.isLogged());
		// return false;
		return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
		Powermanager powermanager=(Powermanager)Appctx.ctx.getBean("powermanager");
		if(powermanager.isLogged()){
			SecurityUser su=(SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<SModules> sm=su.getPowerList();
			modelAndView.addObject("powerlist", sm);
		}else{
			List<SModules> sm=powermanager.getSysPowerList();
			modelAndView.addObject("powerlist", sm);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet
		// 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}

}