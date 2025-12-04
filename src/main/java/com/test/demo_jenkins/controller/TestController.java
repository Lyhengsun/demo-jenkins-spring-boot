package com.test.demo_jenkins.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {
    @GetMapping
    public String getTest() {
        return "test";
    }
    
    @GetMapping("/second")
    public String getTestSecond() {
        return "test second";
    }
}
