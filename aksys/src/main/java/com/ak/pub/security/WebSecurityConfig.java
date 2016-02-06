package com.ak.pub.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig
		extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

//	@Autowired
//	private Powermanager powermanager;

	@Autowired
	@Qualifier("dataSource1")
	private DataSource dataSource1;

	/**
	 * 如不进行配置，则js 和css文件无法加载
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/css/**");
	}

	/**
	 * @param http
	 * @exception Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		String str = "/";
//		List<SModules> l=powermanager.getSysPowerList();
//		for(SModules s:l)str+=","+s.getUrl();
//		System.out.println(str);
		
		// http.authorizeRequests().antMatchers("/", "/learn/home").permitAll()
		http.authorizeRequests().antMatchers("/","/pub/**").permitAll()
   		.anyRequest().authenticated()
   		.and()
   		.formLogin()
   		.loginPage("/login")		
   		.permitAll()
   		.successHandler(loginSuccessHandler())
   		.and()
   		.logout()
   		.logoutSuccessUrl("/pub/home")
   		.permitAll()
   		.invalidateHttpSession(true)
   		.and()
   		.rememberMe()
   		.tokenValiditySeconds(1209600)
   		.tokenRepository(tokenRepository());
		// 限制用户多点登录，并没有成功。
		// .and()
		// .sessionManagement()
		// .maximumSessions(1)
		// .sessionRegistry(sessionRegistry())
		// .maxSessionsPreventsLogin(true);

	}

	/**
	 * @param auth
	 * @exception Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth
		// .inMemoryAuthentication()
		// .withUser("user").password("password").roles("USER");
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		auth.eraseCredentials(false);

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	public JdbcTokenRepositoryImpl tokenRepository() {
		JdbcTokenRepositoryImpl j = new JdbcTokenRepositoryImpl();
		j.setDataSource(dataSource1);
		// j.setCreateTableOnStartup(true);
		return j;
	}

	/**
	 * @Bean public SimpleLoginSuccessHandler simpleLoginSuccessHandler(){
	 *       SimpleLoginSuccessHandler s=new SimpleLoginSuccessHandler();
	 *       s.setForwardToDestination(true);
	 *       s.setDefaultTargetUrl("/index.html"); return s; }
	 * @Bean public SessionRegistryImpl sessionRegistry(){ return new
	 *       SessionRegistryImpl(); }
	 * @Bean public static HttpSessionEventPublisher httpSessionEventPublisher()
	 *       { return new HttpSessionEventPublisher(); }
	 */
	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}

}