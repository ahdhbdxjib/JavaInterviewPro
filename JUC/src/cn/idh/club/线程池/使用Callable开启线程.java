package cn.idh.club.线程池;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class 使用Callable开启线程 implements Callable<String> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "coming in");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "the result";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new 使用Callable开启线程());

        Thread t = new Thread(futureTask, "AA");

        t.start();
        String s = "HHHHH";
        System.out.println("s = " + s);
//        这个方法会阻塞，如果没有等到结果就会一直等待
        String end = futureTask.get();
        System.out.println(s + end);
    }
}
