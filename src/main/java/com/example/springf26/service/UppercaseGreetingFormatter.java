package com.example.springf26.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="app.greeting.uppercase", havingValue = "true")
public class UppercaseGreetingFormatter implements GreetingFormatter {
    @Override
    public String format(String text){
        String result = text.toUpperCase();
        return result;
    }
}
