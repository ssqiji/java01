package com.zhql.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 9:04
 */
public class JdkDynamicProxy2 implements InvocationHandler {

    private Object target;

    public JdkDynamicProxy2(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法参数个数为：" + (args != null ? args.length : 0));
        Object ret = method.invoke(target, args);
        System.out.println("方法返回值为：" + (ret != null ? ret : null));
        return ret;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
