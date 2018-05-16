package com.yixiu.designpattern.create._1singleton;

/**
 * 静态内部类单例模式
 * 由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；
 * 同时读取实例的时候不会进行同步，没有性能缺陷；
 * 也不依赖 JDK 版本。
 */
public class StaticSingleton {

    //通过一个静态的内部类实现懒汉加载
    private static class SingletonHolder {
       private   static  final StaticSingleton instance =  new   StaticSingleton();
    }

    private  StaticSingleton() {}

    public  static final StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public  void sayHello() {
        System.out.println("hello,I am StaticSingleton");
    }

}
