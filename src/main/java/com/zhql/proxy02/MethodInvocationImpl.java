package com.zhql.proxy02;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/18 8:51
 */
public class MethodInvocationImpl implements MethodInvocation {

    private TargetMethod targetMethod;
    private List<MethodInterceptor> interceptorList;
    private AtomicInteger index = new AtomicInteger(0);

    @Override
    public Object proceed() throws Throwable {
        // 判断拦截器集合是否执行完毕，如果执行完毕则开始执行被代理接口方法
        if(index.intValue() == interceptorList.size()) {
            return targetMethod.getMethod().invoke(targetMethod.getTarget(), targetMethod.getArgs());
        }
        // 说明还有拦截器没有执行
        MethodInterceptor interceptor = interceptorList.get(index.getAndIncrement());
        return interceptor.invoke(this);
    }

    public MethodInvocationImpl(TargetMethod targetMethod, List<MethodInterceptor> interceptorList) {
        this.targetMethod = targetMethod;
        this.interceptorList = interceptorList;
    }
}
