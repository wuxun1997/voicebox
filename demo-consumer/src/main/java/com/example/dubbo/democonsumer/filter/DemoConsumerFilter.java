package com.example.dubbo.democonsumer.filter;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Activate(group = Constants.CONSUMER, value = "ipFilter")
public class DemoConsumerFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(DemoConsumerFilter.class);

    private static List<String> whiteIps = new ArrayList<>();

    // 获取白名单列表
    static {
        whiteIps.add("127.0.0.1");
        whiteIps.add("10.73.166.165");
        whiteIps.add("192.168.100.12");
        whiteIps.add("192.168.1.5");
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        // 获取调用方ip
        String clientIp = RpcContext.getContext().getRemoteHost();


        // 在白名单内则调用消费者接口，否则退出
        if (whiteIps != null && whiteIps.size() > 0 && whiteIps.contains(clientIp)) {
            LOGGER.warn("----The consumer {}---- ip {} is in whiteIpList.", invocation.getMethodName(), clientIp);
            return invoker.invoke(invocation);
        } else {
            LOGGER.error("---consumer : {}--- can not be invoke for ip : {} is not allowed.", invocation.getMethodName(), clientIp);
            return new RpcResult();
        }
    }
}
