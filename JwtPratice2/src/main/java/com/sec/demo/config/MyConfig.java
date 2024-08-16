package com.sec.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sec.demo.jwt.JwtFilter;
import com.sec.demo.service.userService;

@Configuration
public class MyConfig {
	
	@Autowired
	JwtFilter jf;
	
	@Autowired
	userService us;

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/login").permitAll()
			.anyRequest().authenticated();
		
		http.addFilterBefore(jf, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager AM(AuthenticationConfiguration auth) throws Exception{
		return auth.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

	public void AMB(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("AMB....");
		auth.userDetailsService(us).passwordEncoder(passwordEncoder());
	}
	
	
}
