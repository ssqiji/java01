package com.zhql.proxy02;

import java.lang.reflect.Method;

/**
 * 目标方法封装
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 17:47
 */
public class TargetMethod {
    /**
     * 目标对象，被代理对象
     */
    private Object target;
    /**
     * 目标方法
     */
    private Method method;
    /**
     * 方法参数
     */
    private Object[] args;

    public TargetMethod(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

}
