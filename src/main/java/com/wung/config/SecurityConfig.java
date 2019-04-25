/*
 * Copyright (C), 2011-2018.
 */
package com.wung.config;

import com.wung.model.Reader;
import com.wung.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义Security配置，覆盖Spring Boot的默认配置。
 *
 * @author wung 2018/9/28.
 */

// 注释掉这个配置类，会弹security的默认登录页
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ReaderRepository readerRepository;
	
	/**
	 * 查询用户
	 *
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(
				username -> {
					Reader reader = new Reader();
					reader.setUsername("user");
					return reader;
					
					// Reader reader = readerRepository.findOne(username);
					// if (reader != null) {
					// 	return reader;
					// }
					// throw new UsernameNotFoundException("用户名不存在！");
				}
		);
	}
	
	/**
	 * 设置访问权限
	 *
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		
		http.authorizeRequests()
				// / 路径的请求必须认证且有READER角色
				// .antMatchers("/").access("hasRole('READER')")
				// 其它路径都开放
				.antMatchers("/**").permitAll()

				.and()

				// 设置登录表单的路径，以及登录失败页
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true");
		
	}
}
