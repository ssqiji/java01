package com.zhql.proxy02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/18 8:47
 */
public class JdkDynamicProxy implements InvocationHandler {

    /**
     * 目标对象，被代理对象
     */
    private Object target;

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 方法拦截器列表
     */
    private List<MethodInterceptor> interceptorList = new ArrayList<>();

    public void addInterceptor(MethodInterceptor interceptor) {
        interceptorList.add(interceptor);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TargetMethod targetMethod = new TargetMethod(target, method, args);
        MethodInvocationImpl methodInvocation = new MethodInvocationImpl(targetMethod, interceptorList);
        return methodInvocation.proceed();
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
