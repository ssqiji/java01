package com.zhql.proxy02;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/18 9:06
 */
public class TwoInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Two interceptor begin...");
        Object ret = methodInvocation.proceed();
        System.out.println("Two interceptor end...");
        return ret;
    }
}
