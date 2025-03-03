package com.example.greeting.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreetingMessage(String name) {
        return "Hello, " + name + "!";
    }

    public String createGreetingMessage(String message) {
        return "Welcome, " + message;
    }

    public String updateGreetingMessage(String message) {
        return "Updated Greeting: " + message;
    }

    public String deleteGreetingMessage() {
        return "Greeting deleted successfully!";
    }
}
