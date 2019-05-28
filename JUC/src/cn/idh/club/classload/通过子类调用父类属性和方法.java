package cn.idh.club.classload;

/**
 * 虽然使用的是子类调用父类的域和方法，但是并没有使用到子类的中的相关的方法和域，所以不算是对子类的主动使用。
 */
class p4 {
    public static int a = 9;

    static {
        System.out.println("p4 Static block");
    }

    public static void doSth() {
        System.out.println("DO Sthg");
    }

}

class c4 extends p4 {
    public static int b = 8;

    static {
        System.out.println("c4 static block");
    }
}

public class 通过子类调用父类属性和方法 {
    public static void main(String[] args) {
        System.out.println(c4.a);
        c4.doSth();

    }
}
