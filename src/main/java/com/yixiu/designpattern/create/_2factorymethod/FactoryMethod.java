package com.yixiu.designpattern.create._2factorymethod;

/**
 * 工厂方法模式
 * 每一个对象的创建，都一个工厂对应
 */
public class FactoryMethod {

    public static void main(String args[]) throws Exception{

        IFactory factory = null;

        factory = new Mi5Factory();
        factory.produce().run();

        factory = new Mi6Factory();
        factory.produce().run();
    }

}

/**
 * 抽象工厂：生产手机
 * 扩展时，只需要增加实现接口IFactory的工厂类就行了，不需要修改，只需要增加代码。
 */
interface  IFactory {
     IPhone produce();
}

/**
 * 具体工厂：生产小米5
 */
class Mi5Factory  implements  IFactory {
    public  IPhone produce() {
        return  new XiaoMi5();
    }
}

/**
 * 具体工厂：生产小米6
 */
class Mi6Factory  implements  IFactory {
    public IPhone produce() {
        return new XiaoMi6();
    }
}



