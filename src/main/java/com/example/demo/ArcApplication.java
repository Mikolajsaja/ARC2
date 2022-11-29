package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class ArcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArcApplication.class, args);
	}

	@GetMapping("/")
	public String helloPage(){
		return "Hello";
	}

}
