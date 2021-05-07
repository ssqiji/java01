package com.zhql.proxy02;

import com.zhql.proxy.Animal;
import com.zhql.proxy.Cat;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/18 9:07
 */
public class MyTest {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.eat();

        System.out.println("---------------------------");
        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(cat);
        jdkDynamicProxy.addInterceptor(new OneInterceptor());
        jdkDynamicProxy.addInterceptor(new TwoInterceptor());

        Animal proxy = (Animal)jdkDynamicProxy.getProxy();
        proxy.eat();

//        try {
//            byte[] b = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Animal.class});
//            FileOutputStream os = new FileOutputStream("D://$Proxy0.class");
//            os.write(b);
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Proxy.newProxyInstance()
    }
}
