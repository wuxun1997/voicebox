package com.example.dubbo.demoproducer.service;

import com.example.dubbo.demo.domain.DemoBean;
import com.example.dubbo.demo.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 注册并提供服务
 */
@Service
public class DemoServiceImpl implements DemoService {


    @Override
    public String sayHelo(String msg) {
        return "hello " + msg;
    }

    @Override
    public String login(DemoBean demoBean) {

        if (demoBean == null || demoBean.getUserName() == null || demoBean.getPasswd() == null)
        {
            return "parameters error.";
        }
        else if(demoBean.getUserName().equals("wlf") && demoBean.getPasswd().equals("123"))
        {
            return "Welcome: " + demoBean.getUserName();
        }

        return "Login failed.";
    }
}
