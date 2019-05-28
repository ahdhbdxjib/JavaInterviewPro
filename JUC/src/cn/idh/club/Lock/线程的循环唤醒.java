package cn.idh.club.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResourse {
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
//            如果不是A打印则循环， 判断
            while (number != 1) {
                c1.await();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
//            通知 指定某个线程唤醒
//            修改标志位
            number = 2;
//            通知c2
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
//            如果不是A打印则循环， 判断
            while (number != 2) {
                c2.await();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);

            }
//            通知 指定某个线程唤醒
//            修改标志位
            number = 3;
//            通知c2
            c3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
//            如果不是A打印则循环， 判断
            while (number != 3) {
                c3.await();
            }

            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);

            }
//            通知 指定某个线程唤醒
//            修改标志位
            number = 1;
//            通知c2
            c1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class 线程的循环唤醒 {
    public static void main(String[] args) {

        ShareResourse resourse = new ShareResourse();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resourse.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resourse.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                resourse.print15();
            }
        }, "CC").start();
    }

}
