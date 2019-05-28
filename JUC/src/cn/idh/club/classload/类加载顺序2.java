package cn.idh.club.classload;

/**
 * 在这种情况下，即使调用的是子类的静态变量，也不会导致静态代码块的执行，因为变量存放到调用者类中常量池中
 */
class Parent1 {
    public static String str = "hello world";

    static {
        System.out.println("Parent static block");
    }

}

class Chrind1 extends Parent2 {
    public final static String s = "hello du";

    static {
        System.out.println("child static block");
    }

}

public class 类加载顺序2 {
    public static void main(String[] args) {
        System.out.println(Chrind2.s);
    }

}
