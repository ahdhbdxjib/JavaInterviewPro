package cn.idh.club.Lock;

import sun.rmi.runtime.NewThreadAction;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class 手写一个自旋锁 {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
//        如果当前没有线程就即except 为null，则将其设置为近来的线程
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {
        }

    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come out ");
//        释放线程的时候，如果是当前线程，则将其置换为null
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        手写一个自旋锁 demo = new 手写一个自旋锁();
        new Thread(() -> {
            demo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.unlock();
        }, "AAA").start();

        new Thread(() -> {
            demo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.unlock();
        }, "BBB").start();
    }
}
