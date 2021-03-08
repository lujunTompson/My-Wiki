package com.chao.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test.hello:Test}")
    private String testHello;

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Post test, we hope the name is " + name;
    }
}
