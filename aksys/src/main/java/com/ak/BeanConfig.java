package com.ak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ak.pub.TransObj;

@Configuration
public class BeanConfig {

	@Bean
	@Scope("request")
	public TransObj transObj() {
		TransObj ts = new TransObj();
		return ts;
	}	

}
