package com.yixiu.designpattern.create._1singleton;

/**
 * 饿汉单例,一般使用这种方式就行了
 * 优点:简单
 * 缺点:程序初始化时就要加载
 * 饿汉式的创建方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者配置文件的，
 * 在 getInstance() 之前必须调用某个方法设置参数给它，那样这种单例写法就无法使用了。
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    public  void sayHello() {
        System.out.println("hello,I am HungrySingleton");
    }

}
