package com.example.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; //http://localhost:8080/swagger-ui/index.html

@SpringBootApplication//http://localhost:8080/h2/login.do?jsessionid=5e4274966293b29fe451a43972ad0faa
//SELECT * FROM GREETINGS //INSERT INTO greetings (message) VALUES ('Hello from H2 console!');
//UPDATE greetings  // SET message = 'New updated greeting' //WHERE id = 1;
// DELETE FROM greetings WHERE id = 1;
public class GreetingAppDevelopmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(GreetingAppDevelopmentApplication.class, args);
	}
}
