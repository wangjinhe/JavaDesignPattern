package com.yixiu.designpattern.behavior._10mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 持久化例子
 */
public class Persistent {




    public static   void main(String [] args) {
        Object data = "数据";
        PersistentDb persistentDB = new PersistentDb();
        PersistentFile persistentFile = new PersistentFile();
        Midiator midiator = new Midiator();
        midiator.add(persistentDB).add(persistentFile);

        persistentDB.saveData(data,midiator);

        persistentFile.saveData(data,midiator);
    }


}


/**
 * 持久化接口
 */
interface IPersistent {
    void saveData(Object data); // 对象本身持久化
    void saveData(Object data,Midiator midiator); // 通过中介持久化，通知其他同事
}

/**
 * 文件持久化
 */
class PersistentFile implements IPersistent {
    public void saveData(Object data) {
        System.out.println("保存到文件:" + data.toString());
    }
    public void saveData(Object data, Midiator midiator) {
        saveData(data);
        midiator.notifyOther(this,data); // 让自己告诉其他人
    }
}

/**
 * 持久化到数据库
 */
class PersistentDb implements IPersistent {
    public void saveData(Object data) {
        System.out.println("保存到数据库:" + data.toString());
    }
    public void saveData(Object data, Midiator midiator) {
        saveData(data);
        midiator.notifyOther(this,data); // 让自己告诉其他人
    }
}

class Midiator {
    private List<IPersistent> listPersistent ;
    public  Midiator() {
        listPersistent = new ArrayList<IPersistent>();
    }

    // 添加其他持久化同事
    public Midiator add(IPersistent persistent) {
        listPersistent.add(persistent);
        return  this;
    }

    // 通知其他持久化同事
    public void notifyOther(IPersistent persistent,Object data) {
        for (IPersistent p: listPersistent ) {
            if(!persistent.getClass().equals(p.getClass())) {
                p.saveData(data);
            }
        }
    }

}