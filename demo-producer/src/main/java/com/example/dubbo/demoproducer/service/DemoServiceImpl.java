package com.example.dubbo.demoproducer.service;

import com.example.dubbo.demo.service.DemoService;

public class DemoServiceImpl implements DemoService{


    @Override
    public String sayHelo(String msg) {
        return "hello " + msg;
    }
}
