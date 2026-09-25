package com.example.springf26.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.springf26.config.AppProperties;


@Service
@RequiredArgsConstructor
public class HelloService {
    private final AppProperties appProperties;
    private final GreetingFormatter greetingFormatter;

    public String greet(String name){
        String who = (name == null || name.isBlank()) ? appProperties.getDefaultName() : name;
        String text = appProperties.getMessage() + ", " + who + "!";
        return greetingFormatter.format(text);
    }
}
