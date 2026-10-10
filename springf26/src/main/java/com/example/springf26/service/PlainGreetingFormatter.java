package com.example.springf26.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.greeting.uppercase", havingValue = "false", matchIfMissing = true)
public class PlainGreetingFormatter implements GreetingFormatter  {

    @Override
    public String format(String text){
        return text;
    }


}
