package cn.idh.club.并发包工具类;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的使用
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            String temp = String.valueOf(i);

            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "工作----");
            }, temp).start();
        }


        countDownLatch.await();
        System.out.println("结束工作 = " );
    }
}
