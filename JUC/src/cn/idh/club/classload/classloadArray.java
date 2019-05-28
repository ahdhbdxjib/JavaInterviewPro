package cn.idh.club.classload;
/**
 * 对于数组类的对象，当数组中存放啊的是引用对象的时候，类加载器会重新动态的生成一个class（一般会在原来的类前面添加一个L）
 * class [Lcn.idh.club.classload.Parent2;
 * 这样的方式是不会加载原来的类，所以也会导致原来的类是不会被加载的 ,知道真正使用到数组中的相关的域的时候，才会加载
 */

class Parent2 {
    public static String str = "hello world";

    static {
        System.out.println("Parent static block");
    }

}

class Chrind2 extends Parent2 {
    public final static String s = "hello du";

    static {
        System.out.println("child static block");
    }

}

public class classloadArray {
    public static void main(String[] args) {
        Parent2[] parent2s = new Parent2[1];
        System.out.println("parent2s = " + parent2s[0].str);
    }

}
