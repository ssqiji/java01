package com.zhql.proxy01;

import com.zhql.proxy.Animal;
import com.zhql.proxy.Cat;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 16:42
 */
public class HandlerTest {
    public static void main(String[] args) {
        Cat cat = new Cat();

        AbstractHandler headHandler = new AbstractHandler.HeadHandler();
        OneHandler oneHandler = new OneHandler();
        oneHandler.setNextHandler(new TwoHandler());
        headHandler.setNextHandler(oneHandler);

        JdkDynamicProxy dynamicProxy = new JdkDynamicProxy(cat, headHandler);
        Animal proxy = (Animal) dynamicProxy.getProxy();
        proxy.eat();
    }

    public static class OneHandler extends AbstractHandler {
        @Override
        Object invoke(TargetMethod targetMethod) throws Throwable {
            System.out.println("one handler start!");
            Object process = process(targetMethod);
            System.out.println("one handler end!");
            return process;
        }
    }

    public static class TwoHandler extends AbstractHandler {
        @Override
        Object invoke(TargetMethod targetMethod) throws Throwable {
            System.out.println("two handler start!");
            Object process = process(targetMethod);
            System.out.println("two handler end!");
            return process;
        }
    }
}
