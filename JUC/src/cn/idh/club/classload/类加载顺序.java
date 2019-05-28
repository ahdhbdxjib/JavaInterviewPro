package cn.idh.club.classload;

/**
 * 在这种情况下，虽然调用的是子类的构造函数，但是使用的是父类的静态变量，但是并没有主动使用子类中的静态变量
 */
class Parent {
    public static String str = "hello world";

    static {
        System.out.println("Parent static block");
    }

}

class Chrind extends Parent {
    public static String s = "hello du";

    static {
        System.out.println("child static block");
    }

}

public class 类加载顺序 {
    public static void main(String[] args) {
        System.out.println(Chrind.str);
    }

}
