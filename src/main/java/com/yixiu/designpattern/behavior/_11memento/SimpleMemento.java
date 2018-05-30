package com.yixiu.designpattern.behavior._11memento;

public class SimpleMemento {

    public static  void main(String [] args){
        Originator originator = new Originator(); // 发起人
        Caretaker caretaker = new Caretaker(); // 守护者
        originator.setState("状态1");
        caretaker.saveMemento(originator.createMemento()); // 保存状态
        originator.setState("状态2");
        originator.recoverMemento(caretaker.recoverMemento());

        System.out.println("恢复状态：" + originator.getState());


    }
}


/**
 * 备忘录的发起人
 */
class Originator {
    private String state; //状态
    // 创建备忘录
    public  Memento createMemento() {
        return  new Memento(state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void recoverMemento(Memento memento) {
        this.state = memento.getState();
    }

}

/**
 * 备忘录
 */
class Memento {
    private  String state;

    public  Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 守护者：保存备忘录
 */
class Caretaker {
    private Memento memento;

    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
    // 恢复
    public  Memento recoverMemento() {
        return  memento;
    }
}
