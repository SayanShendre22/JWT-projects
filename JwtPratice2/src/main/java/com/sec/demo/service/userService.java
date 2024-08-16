package com.sec.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sec.demo.entity.user;
import com.sec.demo.repo.userRepo;

@Service
public class userService  implements UserDetailsService {
	
	@Autowired
	userRepo ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<user> ou = ur.findByUsername(username);
		
		
		if(ou.get()!=null) {
			user u = ou.get();
			System.out.println("user found in UDS");
			return new User(u.getUsername(), u.getPassword(), new ArrayList<>());
		}
		
		
		throw new UsernameNotFoundException("User nahi milan in UDS");
	}

	
}
