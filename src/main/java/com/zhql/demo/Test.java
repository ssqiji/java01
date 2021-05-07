package com.zhql.demo;

/**
 * 交替输出A-Z 1-26
 * 格式如：A1B2C3D4....
 */
public class Test {

    static final Object lock = new Object();

    public static void main(String[] args) {

        ThreadLocal tl = new ThreadLocal();
        tl.set(new Object());

        new Thread(()->{
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    System.out.println((char)(65 + i));
                    try {
                        lock.wait();
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.println(i);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t2").start();
    }
}
