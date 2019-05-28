package cn.idh.club.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 使用线程池启动线程 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "开始工作");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
