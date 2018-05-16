package com.yixiu.designpattern.create._1singleton;

import org.junit.Test;

public class HungrySingletonTest {
    @Test
    public void sayHello() throws Exception {
        HungrySingleton.getInstance().sayHello();
    }

}