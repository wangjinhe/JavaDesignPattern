package com.yixiu.designpattern.create._1singleton;

/**
 * 简单的单例
 * 文章参考：http://www.importnew.com/21141.html
 *
 */
public class SimpleSingleton {
    private static SimpleSingleton instance;
    private SimpleSingleton(){}

    /**
     * 懒汉式，线程不安全
     * @return
     */
    public static SimpleSingleton getInstance() {
        // 不能使用instance.equals(null)，因为 instance本身就是 null，否则报错   :java.lang.NullPointerException
        if( instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }


    /**
     * 懒汉式，线程安全
     * 因为在任何时候只能有一个线程调用 getInstance() 方法。
     * 但是同步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象时。
     * @return
     */
    public static synchronized SimpleSingleton getInstanceSyn() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

    /**
     * 双重检验锁
     * 问题： instance = new Singleton() 这并非是一个原子操作，也可能出现问题
     * 解决办法是：
     * private volatile static Singleton instance; //声明成 volatile
     * 使用 volatile 的主要原因：禁止指令重排序优化。当volatile变量在写的时候，只有写完成才可以读。
     * @return
     */
    public static SimpleSingleton getSingletonDoubleCheck() {
        if (instance == null) {
            synchronized (SimpleSingleton.class) {
                if (instance == null) {
                    instance = new SimpleSingleton();
                }
            }
        }
        return instance;
    }

    public  void sayHello() {
        System.out.println("hello,I am Singleton");
    }

}
