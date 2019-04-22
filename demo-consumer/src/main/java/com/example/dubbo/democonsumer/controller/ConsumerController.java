package com.example.dubbo.democonsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import  com.example.dubbo.demo.service.DemoService;

@RestController
public class ConsumerController {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHelo(@RequestParam(value="msg") String msg)
    {
        return "hello, world";
    }
}
