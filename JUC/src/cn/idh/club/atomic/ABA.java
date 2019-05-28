package cn.idh.club.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ABA {
    /**
     * 使用两个线程，A - B
     * 线程A等待5秒，访问两次
     * 线程B先修改一次，再将值改回去
     * 此时A
     * @param args
     */

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(100,20019);
        }).start();
        new Thread(()->{

            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
        }).start();
        while (atomicInteger.get() != 20019){
        }
        System.out.println(atomicInteger.get());
    }



}
