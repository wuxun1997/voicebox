package com.example.dubbo.demo.service;


import com.example.dubbo.demo.domain.DemoBean;

/**
 * API
 */
public interface DemoService {

    /**
     * 说hello接口
     * @param msg
     * @return
     */
    String sayHelo(String msg);

    /**
     * 登陆接口
     * @param demoBean
     * @return
     */
    String login(DemoBean demoBean);
}
