# voicebox

## 一个spring boot集成dubbo的小例子

Dubbo Apache Dubbo™ (incubating)是一款高性能Java RPC框架。 Spring Boot 应用场景的开发。同时也整合了 Spring Boot 特性：<br>
自动装配 (比如： 注解驱动, 自动装配等).<br>
Production-Ready (比如： 安全, 健康检查, 外部化配置等).<br>
Apache Dubbo (incubating) |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。<br>

>  

本例子用于展示dubbo的基本特性。<br>

### 1. 接口demo-api包
API被服务消费者和服务提供者共用，所以剥离出来独立打包。<br>
```Java
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
```
### 2. 服务提供者demo-producer包
    这里演示两种注册中心：组播和zk，先看配置文件
```xml
#端口配置
server.port=8181

#Dubbo服务生产者配置
spring.application.name=producer

#组播注册中心，unicast=false指定广播而非单播
dubbo.registry.address=multicast://224.5.6.7:1234?unicast=false

#zk注册中心
#dubbo.registry.address=zookeeper://127.0.0.1:2181

#本地缓存文件
dubbo.registry.file = ${user.home}/dubbo-cache/${spring.application.name}/dubbo.cache

#rpc通信协议
dubbo.protocol.name=dubbo

#rpc通信端口
dubbo.protocol.port=1234

#用于服务暴露的扫描路径
dubbo.scan.base-packages=com.example.dubbo.demoproducer.service

#服务监控
dubbo.monitor.protocol=registry
```
再来看看如何将实现API并注册服务：<br>
```java    
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

        if (demoBean == null)
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
```
    这里实现了demo-api的接口DemoService，并通过@Service将服务注册到注册中心，可以通过@Service(version = "1.0.0")指定服务版本号。<br>
    
### 3. 服务消费者demo-consumer包
同样先看看配置：<br>

```xml
#端口配置
server.port=8182

#Dubbo服务消费者配置
spring.application.name=consumer

#组播注册中心
dubbo.registry.address=multicast://224.5.6.7:1234?unicast=false

#zk注册中心
#dubbo.registry.address=zookeeper://127.0.0.1:2181

#本地缓存文件
dubbo.registry.file = ${user.home}/dubbo-cache/${spring.application.name}/dubbo.cache

#监控中心
dubbo.monitor.protocol=registry
```
    我们可以看到服务消费者的配置比提供者的简单，只需指定注册中心即可，其他的服务发现、服务路由和负载均衡机制均由注册中心来完成。<br>
    当发现服务提供方的调用路径后，消费方直接连通提供方进行rpc调用。<br>
```java    
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
    public String login(DemoBean demoBean)
    {
        return demoService.login(demoBean);
    }
}
```
    服务发现通过@Reference实现，也可以直接指定rpc调用接口的版本号和路径：@Reference(version = "1.0.0", url = "dubbo://127.0.0.1:1234")<br>
    
>  

    最后说下pom文件的jar包依赖，spring boot本身的依赖和服务提供方、消费方对API的依赖就不说了，这里看下集成dubbo需要加入的依赖<br>
```xml    
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.7.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.7.1</version>
        </dependency>    
```        
    目前最新版本是2.7.1，如果注册中心是zk的话，还需再依赖一个jar包：<br>
    
```xml
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-zookeeper</artifactId>
            <version>2.7.1</version>
            <type>pom</type>
        </dependency>
```
        
    至于监控中心，需要本地另外启动，具体参见官网：[Dubbo管理控制台介绍](http://dubbo.apache.org/zh-cn/docs/admin/introduction.html)<br>    
