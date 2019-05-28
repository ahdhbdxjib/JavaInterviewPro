package cn.idh.club.classload.classload_interface;

/**
 * 在接口中，public 和 static已经 final都是默认的
 * 在接口中，并不要求对父类进行初始化
 * 而是真正使用到父类的时候才会初始化
 */
interface Demo {
    public static String p = "helleo";

}

class cDemo implements Demo {
    public static String c = "c _______helleo";
}

class test {
    public static void main(String[] args) {
        //如果直接使用的话，就算删除Demo的class文件也不会发生错误，因为接口，不要求对父类加载
//        System.out.println(cDemo.c);
        cDemo cDemo = new cDemo();
    }

}