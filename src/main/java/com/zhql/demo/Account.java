package com.zhql.demo;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行
 *
 * 容易产生脏读（dirtyRead）
 */
public class Account {

    private Integer userId;
    private String name;
    private double balance;

    public synchronized void set(Integer userId, String name, double balance) {
        this.userId = userId;
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(Integer userId) {
        return balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(()->{
            a.set(1, "张三", 200);
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance(1));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance(1));
    }
}
