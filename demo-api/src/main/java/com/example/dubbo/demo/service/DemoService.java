package com.example.dubbo.demo.service;

import org.springframework.stereotype.Component;

@Component
public interface DemoService {
    String sayHelo(String msg);
}
