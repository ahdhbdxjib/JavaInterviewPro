package cn.idh.club.volatileDemo;

import java.util.concurrent.TimeUnit;

class MyData3{
     volatile int num = 0;
    public void addNum(){
        num ++;
    }
}

/**
 * 使用volatile修饰,不能保证数据的原子性操作哦，所以会出现错误的结果
 * 解决方法就是使用并发包中的Atmic的Integer
 */
public class 测试变量的原子性 {
    public static void main(String[] args) {
        MyData3 data = new MyData3();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.addNum();
                }
            },String.valueOf(i)).start();

        }

        //使用自旋锁，一直等待，所有线程都执行结束
        while (Thread.activeCount() > 2){
            //如果没有就礼让线程
            Thread.yield();
        }
        System.out.println("thread main getNum  " + data.num);
    }
}
