package com.sec.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtPratice1Application implements CommandLineRunner {

	@Autowired
	PasswordEncoder pe;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtPratice1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(pe.encode("sayan"));
	}

}
