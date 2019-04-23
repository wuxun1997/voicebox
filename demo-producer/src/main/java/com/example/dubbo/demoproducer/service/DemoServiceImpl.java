package com.example.dubbo.demoproducer.service;

import com.example.dubbo.demo.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {


    @Override
    public String sayHelo(String msg) {
        return "hello " + msg;
    }
}
