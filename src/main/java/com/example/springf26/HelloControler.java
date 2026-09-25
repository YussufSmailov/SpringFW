package com.example.springf26;

import com.example.springf26.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class HelloControler {
    private final HelloService helloService;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", defaultValue = "World") String name){
        return helloService.greet(name);
    }

}
