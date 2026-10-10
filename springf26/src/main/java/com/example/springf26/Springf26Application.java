package com.example.springf26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Springf26Application {

    public static void main(String[] args) {
        SpringApplication.run(Springf26Application.class, args);
    }

}
