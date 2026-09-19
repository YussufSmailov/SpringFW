package com.example.springf26.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "app.greeting")
public class AppProperties {

    @NotBlank
    private String message;

    @NotBlank
    private String defaultName;
}
