package cn.idh.club;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean Flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingDeque = null;

    //具体使用什么队列让用户来做决定
    public MyResource(BlockingQueue<String> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    public void myPeoduct() throws InterruptedException {
        String data = null;
        boolean retVaule;
        while (Flag) {
            data = atomicInteger.incrementAndGet() + "";
            retVaule = blockingDeque.offer(data, 2l, TimeUnit.SECONDS);
            if (retVaule) {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "失败");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "线程停");
    }

    public void myConsumer() throws InterruptedException {
        String res = null;

        while (Flag) {
            res = blockingDeque.poll(2l, TimeUnit.SECONDS);
            if (null == res || res.equalsIgnoreCase("")) {
                Flag = false;
                System.out.println(Thread.currentThread().getName() + "\t" + "消费超时");
                System.out.println();
                System.out.println();
                return;
            }

            System.out.println(Thread.currentThread().getName() + "\t" + "消费成功:" + res);
        }
    }

    public void stop() {
        this.Flag = false;
    }
}

public class 综合练习 {
    public static void main(String[] args) throws Exception {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "生产者开始工作");
            try {
                myResource.myPeoduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producter").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "消费者开始工作");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        myResource.stop();
    }
}
