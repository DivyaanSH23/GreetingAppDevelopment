package com.example.greeting.controller;

import com.example.greeting.model.Greeting;
import com.example.greeting.service.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greet")
public class HelloController {

    private final AtomicLong counter = new AtomicLong();
    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public String getGreeting(@RequestBody String firstName, String lastName) {

//        String message = greetingService.getGreetingMessage(firstName, lastName);
//        return new Greeting(counter.incrementAndGet(), message);
        return "hello"+firstName+lastName;
    }
}
