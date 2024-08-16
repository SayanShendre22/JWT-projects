package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.user;
import com.example.demo.jwt.JwtHelper;
import com.example.demo.repo.UserRepo;

@RestController
public class MyController {
	
	@Autowired
	JwtHelper JH;
	
	@Autowired
	UserDetailsService uds;
	
	@Autowired
	UserRepo ur;
	
	@RequestMapping(path = "/public", method = RequestMethod.GET)
	public String Public() {
		return "hi i am public user";
	}
	
	@GetMapping("/users")
	public List<user> getusers(Principal p){
		System.out.println("My name is "+p.getName());
		return ur.findAll();
	}
	
	
	@PostMapping("/Login")
	public String Login(@RequestBody user u) {
		
		System.out.println("uds name "+uds.loadUserByUsername(u.getUsername()).toString());
		System.out.println(" u name "+u.toString());
		
		if( u.getUsername().equals(uds.loadUserByUsername(u.getUsername()).getUsername())   &&   u.getPassword().equals(uds.loadUserByUsername(u.getUsername()).getPassword())  ) {
			System.out.println("Login Sucessfully");
			
			return "Token is: "+JH.generateJwtToken(u.getUsername());
			
		}else {
			System.out.println("login fail!!");
			return "Invalid Crediential...!";
		}
		
	}
	

}
