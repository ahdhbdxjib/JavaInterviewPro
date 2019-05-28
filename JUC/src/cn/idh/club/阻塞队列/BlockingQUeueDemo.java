package cn.idh.club.阻塞队列;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class BlockingQUeueDemo {

    @Test
    public void testArrayBlockingQueue() {
        //默认容量是三个
        ArrayBlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);
//超出相关的范围会抛出异常
        try {
            System.out.println(blockingDeque.add("a"));
            System.out.println(blockingDeque.add("b"));
            System.out.println(blockingDeque.add("c"));
            System.out.println(blockingDeque.add("c"));

            System.out.println(blockingDeque.remove());
            System.out.println(blockingDeque.remove());
            System.out.println(blockingDeque.remove());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayBlockingQueue2() {
        //默认容量是三个
        ArrayBlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);
//超出相关的范围不会抛出异常
        try {
            System.out.println(blockingDeque.offer("a"));
            System.out.println(blockingDeque.offer("b"));
            System.out.println(blockingDeque.offer("c"));
            System.out.println(blockingDeque.offer("c"));

            System.out.println(blockingDeque.poll());
            System.out.println(blockingDeque.poll());
            System.out.println(blockingDeque.poll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayBlockingQueue3() throws InterruptedException {
        //默认容量是三个
        ArrayBlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);
//        使用的是具有阻塞功能的方法

        new Thread(() -> {
            try {
                blockingDeque.put("a");
                blockingDeque.put("c");
                blockingDeque.put("b");
                blockingDeque.put("b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
                TimeUnit.SECONDS.sleep(3);
                blockingDeque.take();
                blockingDeque.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
