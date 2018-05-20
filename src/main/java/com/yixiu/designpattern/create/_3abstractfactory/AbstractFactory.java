package com.yixiu.designpattern.create._3abstractfactory;

/**
 * 抽象工厂模式
 */
public class AbstractFactory {

    public static void main(String args[]){

        IPhoneFactory applePhone = new  ApplePhoneFactory();
        applePhone.producePhone();
        applePhone.play();
        applePhone.charge();

        IPhoneFactory miPhone = new MiPhoneFactory();
        miPhone.producePhone();
        miPhone.play();
        miPhone.charge();


    }

}

/**
 * 抽象产品1：手机主机
 */
interface PhoneHost {
    void play(); // 玩游戏
}

/**
 * 苹果手机主机
 */
class ApplePhoneHost implements  PhoneHost
{
    public void play() {
        System.out.println("正在运行苹果手机......");
    }
}

/**
 * 小米手机
 */
class MiPhoneHost implements  PhoneHost
{
    public void play () {
        System.out.println("正在运行小米手机......");
    }
}


/**
 * 抽象产品2：充电器
 */
interface Charger {
    void charge(); // 充电
}

/**
 * 苹果充电器
 */
class AppleCharger implements  Charger {
    public  void charge() {
        System.out.println("苹果手机正在充电......");
    }
}

/**
 * 小米充电器
 */
class MiCharge implements  Charger {
    public void charge() {
        System.out.println("小米手机正在充电......");
    }
}

/**
 * 抽象大工厂：生产手机主机和充电器
 * 手机工厂同时要生产手机主机和充电器，对外只有生产手机的一个接口，因此采用抽象类实现。
 */
abstract  class  IPhoneFactory  {
    private  PhoneHost phoneHost;
    private  Charger charger;

    /**
     * 生产手机主机和充电器，这两个抽象方法在具体的工厂类中实现
     * 注意：这两个方法不应该对外暴露
     * @return
     */
    protected abstract PhoneHost producePhoneHost(); // 生产手机主机
    protected abstract Charger produceCharge(); // 生产充电器

    /**
     * 生产手机主机和充电器
     */
    public  void producePhone()
    {
        phoneHost = producePhoneHost();
        charger =produceCharge();
    }

    public void play()  // 玩游戏
    {
        phoneHost.play();
    }
    public void charge() // 充电
    {
        charger.charge();
    }
}

/**
 * 苹果手机工厂
 */
class ApplePhoneFactory extends IPhoneFactory
{
    @Override
    protected PhoneHost producePhoneHost() {
        return new ApplePhoneHost();
    }

    @Override
   protected   Charger produceCharge() {
        return new AppleCharger();
    }
}

class MiPhoneFactory extends  IPhoneFactory
{
    @Override
    protected PhoneHost producePhoneHost() {
        return new MiPhoneHost();
    }

    @Override
    protected Charger produceCharge() {
        return new MiCharge();
    }
}