package cn.idh.club.volatileDemo;

import java.util.concurrent.TimeUnit;

class MyData1{
    volatile  int num = 0;
    public void addNum(){
        this.num = 60;
    }
}

/**
 * 使用volatile修饰，所以不 会发生死循环
 */
public class 测试volatile的可见性 {
    public static void main(String[] args) {
        MyData1 data = new MyData1();
        new Thread(() -> {
            System.out.println("Thread:"+Thread.currentThread().getName()+" come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addNum();
            System.out.println("Thread:"+Thread.currentThread().getName()+" finish");
        },"AAA").start();

        //主线程将直接使用数据
        while (data.num == 0){
            //如果主线程直接得到数据的话就将会跳出循环
        }
        System.out.println("thread main getNum" + data.num);
    }
}
