package com.yixiu.designpattern.structure._17adapter;

/**
 * 继承适配器：通过继承实现适配器
 */
public class InheritAdapter {

     public static void main(String [] args) {

         Ps4Adapter ps4Adapter = new Ps4Adapter();
         ps4Adapter.usePs4();
     }


}


/**
 * Ps4接口
 * Ps4是一款游戏机，有自己的接口，玩Ps4需要对应的接口
 */
interface  Ps4 {
    void usePs4();
}

/**
 * Usb接口
 * Usb接口是通用的电脑接口
 */
interface Usb {
    void useUsb();
}

class Usber implements  Usb {
    public void useUsb() {
        System.out.println("开始使用Usb接口");
    }
}

/**
 * Ps4适配器
 * 为客户端提供一种使用usb接口的方式(对外调用的是usePs4,实际调用的是useUsb)
 */
class Ps4Adapter extends  Usber implements  Ps4 {
    public void usePs4() {
        useUsb();
    }
}