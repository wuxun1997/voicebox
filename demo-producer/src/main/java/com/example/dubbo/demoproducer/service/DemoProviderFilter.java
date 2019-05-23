package com.example.dubbo.demoproducer.service;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Activate(group = Constants.PROVIDER, value = "logFilter")
public class DemoProviderFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(DemoProviderFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Long startTime = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        LOGGER.warn("----The provider {}---- costs {} millisecond.", invocation.getMethodName(), System.currentTimeMillis() - startTime);
        return result;
    }
}
