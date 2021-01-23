package com.tongji.boying;

import java.util.concurrent.CountDownLatch;

public class ConcurrentTest {
    /*
    CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程执行完后再执行。
    例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有框架服务之后执行。

    CountDownLatch是通过一个计数器来实现的，计数器的初始化值为线程的数量。
    每当一个线程完成了自己的任务后，计数器的值就相应得减1。当计数器到达0时，表示所有的线程都已完成任务，然后在闭锁上等待的线程就可以恢复执行任务。
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        //模拟 10000 人并发请求
        int userCount = 10000;


        CountDownLatch countDownLatch = new CountDownLatch(userCount);

        for (int i = 0; i < userCount; i++) {
            new Thread((() -> {
                post();//发送请求
                countDownLatch.countDown();
            })).start();
        }

        //必须要减到0才能解除、执行下面的代码
        try {
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("所有模拟请求共花费: " + (end - start) + " ms");
    }

    private static void post() {

    }
}
