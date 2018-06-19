package com.yixiu.designpattern.structure._17adapter;

/**
 * 对象适配器例子
 *
 */
public class ObjectAdapter {
    public static void main(String [] args) {

        Ps3Adapter ps3Adapter = new Ps3Adapter(new Usber3());
        ps3Adapter.usePs3();
    }
}

/**
 * Ps3 接口
 * Ps3 是一款游戏机，有自己的接口，玩Ps3 需要对应的接口
 */
interface  Ps3 {
    void usePs3();
}

/**
 * Usb接口
 * Usb接口是通用的电脑接口
 */
interface Usb3 {
    void useUsb3();
}

/**
 * Usb3接口类
 * 目前有一个合适的方法useUsb3
 */
class Usber3 implements  Usb3 {
    public void useUsb3() {
        System.out.println("开始使用Usb3接口");
    }
}

/**
 * 对象适配器
 * 传递一个对象作为内部成员
 */
class Ps3Adapter implements  Ps3 {
    private  Usb3 usb3;
    public Ps3Adapter(Usb3 usb3) {
        this.usb3 = usb3;
    }

    public void usePs3() {
        usb3.useUsb3();
    }
}


