package com.zhql.proxy;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/17 9:35
 */
public class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("猫猫吃猫粮...");
    }
}
