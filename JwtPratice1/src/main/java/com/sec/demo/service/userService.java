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
public class userService implements UserDetailsService {
	
	@Autowired
	userRepo ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<user> ou = ur.findByUsername(username);
		
		if(ou.get()!=null) {
			
			user u = ou.get();
			
			System.out.println("user mil gaya "+u.getPassword()+" "+u.toString());
			
			return u;
			
		}
		
		
		throw new UsernameNotFoundException("user nahi mila");
	}

}
