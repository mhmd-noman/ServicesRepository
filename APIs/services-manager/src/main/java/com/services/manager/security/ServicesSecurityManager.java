package com.services.manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ServicesSecurityManager extends WebSecurityConfigurerAdapter {
	
	private static final String[] PUBLIC_MATCHERS = { 
		"/UFN/**",
		"/**",
		"/"
	};
	
	@Value("${spring.security.user.name}")
	private String username;
	
	@Value("${spring.security.user.password}")
	private String password;
	
	@Value("${spring.security.user.roles}")
	private String role;
	 
	@Autowired
	private ServicesEntryPoint authenticationEntryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(username).password(passwordEncoder().encode(password)).authorities(role);
		auth.inMemoryAuthentication().withUser("iTeamAdmin").password(passwordEncoder().encode("passRoot")).authorities("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().csrf().disable();
		//http.authorizeRequests().anyRequest().permitAll().anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().csrf().disable();;
		http.addFilterAfter(new ServicesSecurityFilter(), BasicAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
