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

    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id).orElse(new Greeting(0L, "Hello, World!"));
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Greeting createGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public Greeting updateGreeting(Long id, Greeting greeting) {
        Optional<Greeting> existingGreeting = greetingRepository.findById(id);
        if (existingGreeting.isPresent()) {
            Greeting updatedGreeting = existingGreeting.get();
            updatedGreeting.setMessage(greeting.getMessage());
            return greetingRepository.save(updatedGreeting);
        } else {
            return new Greeting(0L, "Greeting not found!");
        }
    }

    public String deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return "Greeting deleted successfully!";
        } else {
            return "Greeting not found!";
        }
    }
}
