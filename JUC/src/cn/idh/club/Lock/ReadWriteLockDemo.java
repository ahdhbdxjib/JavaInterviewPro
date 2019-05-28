package cn.idh.club.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Mycache mycache = new Mycache();
        for (int i = 0; i < 5; i++) {
            final String temp = i + "";
            new Thread(() -> {
                mycache.put(temp, temp);
            }, temp).start();
        }

        for (int i = 0; i < 5; i++) {
            final String temp = i + "";
            new Thread(() -> {
                mycache.get(temp);
            }, temp).start();
        }
    }

}

//资源类
class Mycache {
    //用于存储数据
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        try {
            lock.writeLock().lock();
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + "开始写操作" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "读操作");
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完成" + o.toString());

        } finally {
            lock.readLock().unlock();
        }
    }


}