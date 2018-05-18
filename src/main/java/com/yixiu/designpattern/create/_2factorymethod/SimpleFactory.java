package com.yixiu.designpattern.create._2factorymethod;

/**
 * 简单工厂模式
 * 很多小需求，使用简单工厂模式就能够满足需求
 */
public class SimpleFactory {

    public static void main(String args[]) throws Exception{
        PhoneFactory.producePhone(PhoneProductEnum.Mi5.name()).run();

        PhoneFactory.producePhone(PhoneProductEnum.Mi6.name()).run();
    }

}

/**
 * 抽象产品：手机
 */
interface IPhone {
    void run();
}

/**
 * 手机型号枚举
 * 将可能变化的变量放在枚举中，方便维护
 */
enum PhoneProductEnum {
    Mi5("xiaomi5"),Mi6("xiaomi6");
    private  String name;
    PhoneProductEnum(String name) {
        this.name = name;
    }
}


/**
 * 具体产品：小米5
 */
class XiaoMi5 implements  IPhone {
    public  void run() {
        System.out.println("正在运行小米5");
    }
}

/**
 * 具体产品：小米6
 */
class XiaoMi6 implements  IPhone {
    public  void run() {
        System.out.println("正在运行小米6");
    }
}

/**
 * 工厂：由该工厂负责创建具体的对象
 * 扩展时，在这个函数中增加分支即可。
 */
class PhoneFactory {

    public  static IPhone producePhone(String product)  throws  Exception {
        IPhone phone = null;
        if(product.equals(PhoneProductEnum.Mi5.name())) {
            phone = new XiaoMi5();
        } else if(product.equals(PhoneProductEnum.Mi6.name())) {
            phone = new XiaoMi6();
        } else {
            throw  new Exception("no such class");
        }
        return  phone;
    }


}

