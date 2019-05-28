package cn.idh.club.阻塞队列;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程操作资源类
 * 使用两个线程，将一个数加1做五次，减一做五次
 */
public class ProConsumer_Basic {
    public static void main(String[] args) {
        DataCon data = new DataCon();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}


class DataCon {
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 增加操作
     */
    public void increase() throws InterruptedException {
        try {
            lock.lock();
//        使用循环防止假唤醒
            while (number != 0) {
//若不等于0就等待，知道被消费了再生产
                condition.await();
            }
//        否则，就直接相加就可
            number++;
            System.out.println(Thread.currentThread().getName() + "\t Number ++" + number);
//        唤醒所有线程
            condition.signalAll();


        } finally {
            lock.unlock();
        }

    }

    /**
     * 減少擦操作
     */
    public void decrease() throws InterruptedException {
        try {
            lock.lock();
//        使用循环防止假唤醒
            while (number == 0) {
//若等于0就等待
                condition.await();
            }
//        否则
            number--;
            System.out.println(Thread.currentThread().getName() + "\t Number --" + number);
//        唤醒所有线程
            condition.signalAll();


        } finally {
            lock.unlock();
        }

    }

}

