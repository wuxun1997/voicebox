package com.example.dubbo.democonsumer.controller;

import com.example.dubbo.demo.domain.DemoBean;
import com.example.dubbo.demo.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * 服务消费者
 */
@RestController
public class ConsumerController {

    // 引入API
    @Reference(check = false)
    DemoService demoService;

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHelo(@RequestParam(value = "msg") String msg) {
        return demoService.sayHelo(msg);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(DemoBean demoBean) {
        return demoService.login(demoBean);
    }
}
