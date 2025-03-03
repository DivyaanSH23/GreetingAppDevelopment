package com.example.greeting.service;

import com.example.greeting.model.Greeting;
import com.example.greeting.repository.GreetingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreeting(String firstName, String lastName) {
        String message;
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null && !firstName.isEmpty()) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null && !lastName.isEmpty()) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello, World!";
        }
        return new Greeting(null, message);
    }

    public Greeting createGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElse(new Greeting(0L, "Greeting not found!"));
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Greeting updateGreeting(Long id, Greeting greeting) {
        Optional<Greeting> existing = greetingRepository.findById(id);
        if (existing.isPresent()) {
            Greeting updated = existing.get();
            updated.setMessage(greeting.getMessage());
            return greetingRepository.save(updated);
        }
        return new Greeting(0L, "Greeting not found!");
    }

    public String deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return "Greeting deleted successfully!";
        }
        return "Greeting not found!";
    }
}
