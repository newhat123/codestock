package com.ak;

import javax.sql.DataSource;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.AjaxThymeleafViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.ak.pub.DynamicDataSource;

//测试

/** 动态数据源部分包括： 1、
 * 数据源和动态数据源定义，在本类实现。一个名为dataSource的Bean即可被系统接受作为dataSource。注意要有@Primary
 * 使用DynamicDataSource dataSource()维护数据源列表
 * 2、DataSourceSwitcher，使用contextHolder来向系统存储数据源名，程序中使用它即可切换数据源。
 * 如DataSourceSwitcher.setDataSource("dataSource2");
 * 3、DynamicDataSource，继承AbstractRoutingDataSource，通过注册为
 * dataSource()成为系统数据源，动态取得数据源。
 */
@Configuration 
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class RootConfig {
    
   @Bean
   public DataSource dataSource1() {
   
   	org.springframework.jdbc.datasource.DriverManagerDataSource ds = new org.springframework.jdbc.datasource.DriverManagerDataSource();
   	ds.setDriverClassName("com.mysql.jdbc.Driver");
   	ds.setUrl("jdbc:mysql://localhost:3306/akdbs");
   	ds.setUsername("root");
   	ds.setPassword("wsdgz");
   	return ds;
   }
   
    
   @Bean
   public DataSource dataSource2() {
   
   	org.springframework.jdbc.datasource.DriverManagerDataSource ds = new org.springframework.jdbc.datasource.DriverManagerDataSource();
   	ds.setDriverClassName("com.mysql.jdbc.Driver");
   	ds.setUrl("jdbc:mysql://localhost:3306/test");
   	ds.setUsername("root");
   	ds.setPassword("wsdgz");
   	return ds;
   }
   
    
   @Bean 
   @Primary
   public DynamicDataSource dataSource() {
   
   	DynamicDataSource dynamicDataSource = new DynamicDataSource();
   
   	// Map<Object, Object> map = new HashMap<Object, Object>();
   	// map.put("dataSource1", dataSource1());
   	// map.put("dataSource2", dataSource2());
   	//
   	// dynamicDataSource.setTargetDataSources(map);
   	// dynamicDataSource.setDefaultTargetDataSource(dataSource1());
   
   	dynamicDataSource.addTargetDataSource("dataSource1", dataSource1());
   	dynamicDataSource.addTargetDataSource("dataSource2", dataSource2());
   	dynamicDataSource.setDefaultTargetDataSource(dataSource1());
   	return dynamicDataSource;
   }
   
   /** 
    * @Bean
    *  public LocalSessionFactoryBean sessionFactory(){
    *  LocalSessionFactoryBean stt=new LocalSessionFactoryBean();
    *  stt.setDataSource(dataSource());
    *  return stt;
    *  }
    *  用来绑定页面使用的#{...} */
   @Bean
   public ResourceBundleMessageSource messageSource() {
   	ResourceBundleMessageSource rBundleMessageSource = new ResourceBundleMessageSource();
   	rBundleMessageSource.setBasename("Messages");
   	return rBundleMessageSource;
   }
   
//   @Bean
//   public ServletContextTemplateResolver templateResolver(){
//	   ServletContextTemplateResolver sc=new ServletContextTemplateResolver();
//	   sc.setPrefix("/templates/");
//	   sc.setSuffix(".html");
//	   sc.setTemplateMode("HTML5");
//	   sc.setCharacterEncoding("UTF-8");
//	   return sc;
//   }
//   
//   @Bean
//   public SpringTemplateEngine templateEngine(){
//	   SpringTemplateEngine st=new SpringTemplateEngine();
//	   st.setTemplateResolver(templateResolver());
//	   return st;
//   }
//   
//   
//   @Bean
//   public ThymeleafViewResolver thymeleafViewResolver(){
//	   ThymeleafViewResolver tv=new ThymeleafViewResolver();
//	   tv.setTemplateEngine(templateEngine());
//	   tv.setCharacterEncoding("UTF-8");
//	   return tv;
//   }
//   
//   
//   @Bean
//   public AjaxThymeleafViewResolver ajaxThymeleafViewResolver(){
//	   AjaxThymeleafViewResolver av=new AjaxThymeleafViewResolver();
//	   av.setTemplateEngine(templateEngine());	   
//	   return av;
//   }
   
   
   
//   @Bean
//   public LocalValidatorFactoryBean validator(){
//	   LocalValidatorFactoryBean v=new LocalValidatorFactoryBean();
//	   v.setProviderClass(HibernateValidator.class);
//	   v.setValidationMessageSource(validatemessagesSource());
//	   return v;
//   }
   
   
//   @Bean
//   public ReloadableResourceBundleMessageSource validatemessagesSource(){	   
//	   ReloadableResourceBundleMessageSource m=new ReloadableResourceBundleMessageSource();
//	   m.setBasename("ValidationMessages");
//	   return m;
//   }
   
  

}

//	@Bean
//	public PersistentTokenBasedRememberMeServices prembermeServices(){
//		PersistentTokenBasedRememberMeServices p=new PersistentTokenBasedRememberMeServices("myApp", customUserDetailsService, tokenRepository);
//		p.setParameter("remember-me");
//		p.setCookieName("remember-me");	
//		return p;
//	}
//	@Bean
//	public RememberMeAuthenticationFilter rememberMeAuthenticationFilter(){
//		
//		AuthenticationManager a=(AuthenticationManager)Appctx.ctx.getBean("authenticationManager");
//		RememberMeServices b=(RememberMeServices)Appctx.ctx.getBean("prembermeServices");
//		
//		//RememberMeAuthenticationFilter r=new RememberMeAuthenticationFilter();
//		//System.out.println(a==null?"is null":"not null");
//		return new RememberMeAuthenticationFilter(a,b);
//	}