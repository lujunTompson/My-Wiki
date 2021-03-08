package com.chao.wiki.controller;

import com.chao.wiki.domain.Test;
import com.chao.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

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

    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
