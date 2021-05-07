package com.zhql.lock;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2021/2/8 22:21
 */
public class Demo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockTestThreadA());
        Thread t2 = new Thread(new DeadLockTestThreadB());
        t1.setName("线程A");
        t2.setName("线程B");
        t1.start();
        t2.start();
    }
}

/**
 * 死锁测试线程A
 */
class DeadLockTestThreadA implements Runnable {
    @Override
    public void run() {
        synchronized (Resource.r1) {
            System.out.println("线程A持有资源r1");
            System.out.println("线程A准备请求资源r2...");
            synchronized (Resource.r2) {
                System.out.println("线程A请求资源r2成功...");
            }
        }
    }
}

/**
 * 死锁测试线程B
 */
class DeadLockTestThreadB implements Runnable {
    @Override
    public void run() {
        synchronized (Resource.r2) {
            System.out.println("线程B持有资源r2");
            System.out.println("线程B准备请求资源r1...");
            synchronized (Resource.r1) {
                System.out.println("线程B请求资源r1成功...");
            }
        }
    }
}

/**
 * 资源类
 */
class Resource {
    static Object r1 = new Object();
    static Object r2 = new Object();
}
