package com.zhql.proxy02;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/18 8:44
 */
public interface MethodInterceptor {

    /**
     * 方法拦截器接口，增强逻辑，全部写在里面
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object invoke(MethodInvocation invocation) throws Throwable;
}
