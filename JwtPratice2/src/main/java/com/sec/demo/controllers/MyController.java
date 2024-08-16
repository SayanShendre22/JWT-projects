package com.sec.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sec.demo.entity.user;
import com.sec.demo.jwt.Helper;
import com.sec.demo.repo.userRepo;
import com.sec.demo.service.userService;

@RestController
public class MyController {

	@Autowired
	AuthenticationManager manager;

	@Autowired
	userRepo ur;

	@Autowired
	userService us;

	@Autowired
	Helper h;

	@RequestMapping("/samosa")
	public String Hello(Principal p) {
		System.out.println("samosa delivered!!");
		return "here is your samosa Mr " + p.getName();
	}

	@PostMapping("/login")
	public String login(@RequestBody user u) {

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u.getUsername(),
				u.getPassword());
		
		try {
			Object nu =  manager.authenticate(auth);
			
			user u1 = ur.findByUsername(u.getUsername()).get();

			String token = h.generateToken(u1.getUsername());

			return token;
			
		} catch (Exception e) {
			return "error hai bhai";
		}

	}

}
