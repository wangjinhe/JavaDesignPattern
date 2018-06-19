package com.yixiu.designpattern.structure._17adapter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

/**
 * 接口适配器
 *
 * 经典案例：
 * (1) MouseListener, MouseWheelListener, MouseMotionListener  这3个监听有很多接口
 *  MouseAdapter 这个适配器继承了这3个接口，针对方法空实现
 *  针对按钮addActionListener，当只需要监听一个事件时，只需要实现一个方法即可。
 *
 ;
 */
public class InterfaceAdapter {
    public static void main(String [] args) {

        MyButton myButton = new MyButton();
        myButton.addListener(new MyMouseAdapter() {
            @Override
            public void click() {
                System.out.println("监听单击");
            }
        });
    }

}


interface  MyMouseListener {
    void click(); // 单击
    void dbClick(); // 双节
    void popClick(); // 右键菜单
}

/**
 * 接口适配器的空实现
 */
abstract class MyMouseAdapter implements  MyMouseListener   {
    public void click() {

    }

    public void dbClick() {

    }

    public void popClick() {

    }
}

/**
 * 按钮类
 */
class MyButton {

    // 添加监听
    public void addListener(MyMouseListener myMouseListener) {
        System.out.println("添加监听事件");

        // 添加监听后，就可以调用监听事件
        myMouseListener.click();
        myMouseListener.dbClick();
        myMouseListener.popClick();
    }
}