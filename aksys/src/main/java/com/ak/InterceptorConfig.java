package com.ak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ak.pub.MyInterceptor1;
import com.ak.pub.powermg.Powermanager;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
