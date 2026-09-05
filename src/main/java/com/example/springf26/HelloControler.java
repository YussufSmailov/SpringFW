package com.example.springf26;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloControler {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", defaultValue = "World") String name){
        return "Hello, " + name + " !";
    }
}
