package cn.idh.club;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestDemo {
    @Test
    public void testThread() {
        System.out.println(2222);

        Lock lock = new ReentrantLock();
        lock.lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
