package com.example.demo.myService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.model.user;

@Service
public class MyService {

	private List<user> store = new ArrayList<>();
	
	public MyService() {
		store.add(new user(UUID.randomUUID().toString(),"Sayan","sayan@123"));
		store.add(new user(UUID.randomUUID().toString(),"Sakshi","sakshi@123"));
		store.add(new user(UUID.randomUUID().toString(),"mahesh","mahesh@123"));
		store.add(new user(UUID.randomUUID().toString(),"bala","bala@123"));
	}
	
	
	public List<user> getStore() {
		return store;
	}
	
	
}
