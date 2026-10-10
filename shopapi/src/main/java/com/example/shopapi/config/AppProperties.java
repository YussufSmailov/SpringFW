package com.example.shopapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "app")
public record AppProperties(String currency, boolean seedData) {
}
