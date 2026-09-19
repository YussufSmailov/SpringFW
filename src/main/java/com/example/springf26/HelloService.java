package com.example.springf26;
import org.springframework.stereotype.Service;
import com.example.springf26.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;





@Service
public class HelloService {
    private final AppProperties appProperties;
    @Autowired
    public HelloService(AppProperties appProperties){
        this.appProperties = appProperties;
    }
    public String greet(String name){
        String who = (name == null || name.isBlank()) ? appProperties.getDefaultName() : name;
        return appProperties.getMessage() + ", " + who + "!";
    }
}
