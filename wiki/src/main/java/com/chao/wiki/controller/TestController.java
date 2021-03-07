package com.chao.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Post test, we hope the name is " + name;
    }
}
