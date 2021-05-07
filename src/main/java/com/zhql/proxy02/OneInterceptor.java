package com.zhql.proxy02;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/18 9:04
 */
public class OneInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("One interceptor begin...");
        Object ret = methodInvocation.proceed();
        System.out.println("One interceptor end...");
        return ret;
    }
}
