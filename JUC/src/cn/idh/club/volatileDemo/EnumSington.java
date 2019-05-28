package cn.idh.club.volatileDemo;


/**
 * 使用枚举类的时候，JVM会在底层创建实例不会造成线程不安全
 *
 * 使用这方法兼顾了前面两种的有点，延迟加载，而且不会造成线程安全的问题
 */
public class EnumSington {
    private EnumSington(){}
    private enum Sington{
        INSTANCE;
        private  EnumSington sington;
        Sington(){
            sington = new EnumSington();
        }
        public EnumSington getInstance(){
            return sington;
        }
    }

    public EnumSington getInstance(){
        return Sington.INSTANCE.getInstance();
    }
}
