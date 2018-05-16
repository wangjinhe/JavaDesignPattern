package com.yixiu.designpattern.create._1singleton;

import org.junit.Test;

public class StaticSingletonTest {
    @Test
    public void sayHello() throws Exception {
        StaticSingleton.getInstance().sayHello();
    }

}