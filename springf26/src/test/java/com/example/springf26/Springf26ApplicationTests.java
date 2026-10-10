package com.example.springf26;

import com.example.springf26.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Springf26ApplicationTests {

    @Autowired
    private HelloService helloService;

    @Test
    void contextLoads() {
    }

    @Test
    void greetsInUppercaseWhenFlagIsTrue() {
        assertThat(helloService.greet("Bob")).isEqualTo("HELLO (TEST), BOB!");
    }

    @Test
    void usesDefaultNameWhenNameIsBlank() {
        assertThat(helloService.greet("")).isEqualTo("HELLO (TEST), TESTER!");
    }


}
