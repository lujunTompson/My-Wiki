package com.chao.wiki.controller;

import com.chao.wiki.domain.Demo;
import com.chao.wiki.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;


    @GetMapping("/list1")
    public List<Demo> list() {
        return demoService.list();
    }
}
