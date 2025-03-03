package com.example.greeting.service;

import com.example.greeting.model.Greeting;
import com.example.greeting.repository.GreetingRepository;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // Method to find a Greeting by Id
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElse(new Greeting(0L, "Greeting not found!"));
    }

    // Other methods (create, update, delete) can be here...
}
