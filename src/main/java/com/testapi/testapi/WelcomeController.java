package com.testapi.testapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class WelcomeController {

    @GetMapping
    public String helloWord(){
        return "Welcome to RestApi";
    }
}
