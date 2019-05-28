package cn.idh.club.classload;

class CL {
    static {
        System.out.println("CL static block");
    }
}

/**
 * 对于使用classLoader和CLass.forName（） 加载class，两者有区别
 * 使用classLoader的时候，虽然将类加载了，但是并不会将类初始化，、
 * 使用class.forName不仅会加载类，而且会初始化数据
 */

public class ClassLoader_ClassForName {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = loader.loadClass("cn.idh.club.classload.CL");
        System.out.println("aClass = " + aClass);
        System.out.println("--------------------------");
        Class<?> aClass1 = Class.forName("cn.idh.club.classload.CL");
        System.out.println("aClass1 = " + aClass1);
    }
}
