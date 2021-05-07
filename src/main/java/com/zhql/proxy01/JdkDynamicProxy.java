package com.zhql.proxy01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 9:04
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;
    private AbstractHandler headHandler;

    public JdkDynamicProxy(Object target, AbstractHandler headHandler) {
        this.target = target;
        this.headHandler = headHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TargetMethod targetMethod = new TargetMethod();
        targetMethod.setMethod(method);
        targetMethod.setTarget(target);
        targetMethod.setArgs(args);
        return headHandler.process(targetMethod);
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
