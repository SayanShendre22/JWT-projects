package com.example.demo.myController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.user;
import com.example.demo.myService.MyService;

@RestController
@RequestMapping("/home")
public class MyController {

	@Autowired
	private MyService myService;
	
	@GetMapping("/users")
	public List<user> getUser() {
//		System.out.println("hi i am a new user");
		return myService.getStore();
	}
	
	
	@GetMapping("/current_user")
	public String current_user(Principal p) {
		return p.getName();
	}
	
}
