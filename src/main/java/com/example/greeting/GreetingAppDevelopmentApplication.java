package com.example.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreetingAppDevelopmentApplication { // curl -X GET "http://localhost:8080/greet?firstName=John&lastName=Doe"
												// curl -X GET "http://localhost:8080/greet?firstName=John"
												// curl -X GET "http://localhost:8080/greet?lastName=Doe"
												// curl -X GET "http://localhost:8080/greet"
	public static void main(String[] args) {
		SpringApplication.run(GreetingAppDevelopmentApplication.class, args);
	}

}
