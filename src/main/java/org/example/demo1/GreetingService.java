package org.example.demo1;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class GreetingService {
    public String getGreeting() {
        return "Hello from CDI!";
    }
}
