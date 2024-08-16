package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.user;

public interface UserRepo  extends JpaRepository<user, Integer> {

	List<user> findByUsername(String username);
	
}
