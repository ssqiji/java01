package com.zhql.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 9:34
 */
public class ProxyTest {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.eat();

        System.out.println("--------------------");

        JdkDynamicProxy dynamicProxy = new JdkDynamicProxy(cat);

        Animal proxy = (Animal) dynamicProxy.getProxy();
        try {
            byte[] b = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Animal.class});
            FileOutputStream os = new FileOutputStream("D://$Proxy0.class");
            os.write(b);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        proxy.eat();
        System.out.println(proxy.getClass());
        System.out.println("--------------------");

        JdkDynamicProxy2 dynamicProxy2 = new JdkDynamicProxy2(proxy);
        Animal proxy2 = (Animal)dynamicProxy2.getProxy();
        proxy2.eat();
    }
}
