package com.example.dubbo.demoproducer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.demo.service.DemoService;
import com.example.dubbo.demoproducer.service.DemoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoProducerApplicationTests {

    @Reference(check = false)
    DemoService demoService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testProducer(){
        System.out.println(demoService.sayHelo("wulinfeng"));
    }
}
