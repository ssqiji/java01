package com.zhql.proxy02;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 17:49
 */
public interface MethodInvocation {

    /**
     * 驱动拦截器链，执行增强逻辑 + 被代理方法调用
     * @return
     * @throws Throwable
     */
    Object proceed() throws Throwable;
}
