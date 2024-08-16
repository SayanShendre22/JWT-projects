package com.sec.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class user {
	
	@Id
	int id;
	String username;
	String password;
	public user(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
