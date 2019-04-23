package com.example.dubbo.democonsumer.controller;

import com.example.dubbo.demo.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Reference(check = false)
    DemoService demoService;

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHelo(@RequestParam(value = "msg") String msg) {
        return demoService.sayHelo(msg);
    }
}
