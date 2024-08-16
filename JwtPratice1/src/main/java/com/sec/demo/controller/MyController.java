package com.sec.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sec.demo.config.MyConfig;
import com.sec.demo.entity.user;
import com.sec.demo.jwt.Helper;
import com.sec.demo.repo.userRepo;

@RestController
public class MyController {
	
	@Autowired
	userRepo ur;
	
	@Autowired
	Helper h;
	
	@Autowired
	AuthenticationManager manager;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello motu";
	}
	
	@RequestMapping("/private")
	public String Private(Principal p) {
		return "Hello i am Private "+p.getName();
	}
	
	@PostMapping("/login")
	public String login(@RequestBody user req) {
		
		this.doAuthenticate(req.getUsername(),req.getPassword());
		
		user user  = ur.findByUsername(req.getUsername()).get();
		
		
		if(user!=null) {
			System.out.println("found====================== "+user.getUsername());
			String token =h.generateTokenByUserName(user.getUsername());
			return token;
		}
		return "Invalid user and pass";

	}
	
	
	public void doAuthenticate(String name,String pass) {
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, pass);
		
		try {
			manager.authenticate(auth);
			System.out.println("doneee...");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}