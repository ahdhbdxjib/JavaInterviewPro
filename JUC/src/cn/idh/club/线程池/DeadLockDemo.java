package cn.idh.club.线程池;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo implements Runnable {
    String lockA;
    String lockB;

    public DeadLockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t" + "得到" + lockA + "尝试获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t" + "得到" + lockA + "尝试获取" + lockB);
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLockDemo(lockA, lockB), "AA").start();
        new Thread(new DeadLockDemo(lockB, lockA), "BB").start();
    }


}
