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

    // Constructor-based Dependency Injection
    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        String message = greetingService.getGreetingMessage(name);
        return new Greeting(counter.incrementAndGet(), message);
    }

    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        String message = greetingService.createGreetingMessage(greeting.getMessage());
        return new Greeting(counter.incrementAndGet(), message);
    }

    @PutMapping
    public Greeting updateGreeting(@RequestBody Greeting greeting) {
        String message = greetingService.updateGreetingMessage(greeting.getMessage());
        return new Greeting(counter.incrementAndGet(), message);
    }

    @DeleteMapping
    public Greeting deleteGreeting() {
        String message = greetingService.deleteGreetingMessage();
        return new Greeting(0, message);
    }
}
