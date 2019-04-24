package com.example.dubbo.demo.service;


import com.example.dubbo.demo.domain.DemoBean;

public interface DemoService {
    String sayHelo(String msg);

    String login(DemoBean demoBean);
}
