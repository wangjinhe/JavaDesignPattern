package com.yixiu.designpattern.behavior._7command;

import ch.qos.logback.core.joran.spi.XMLUtil;

import java.util.ArrayList;
import java.util.List;




class Client {
    public static void main(String args[]) {
        FBSettingWindow fbsw = new FBSettingWindow("功能键设置");

        FunctionButton fb1,fb2;
        fb1 = new FunctionButton("功能键1");
        fb2 = new FunctionButton("功能键2");

        Command command1,command2;
        //下面的命令，可以通过读取配置文件和反射生成具体命令对象，这里直接初始化了

        command1 =  new MinimizeCommand();
        command2 =  new HelpCommand();

        // 前面的和功能1绑定，后面的和功能2绑定
        //将命令对象注入功能键
        fb1.setCommand(command1);
        fb2.setCommand(command2);

        fbsw.addFunctionButton(fb1);
        fbsw.addFunctionButton(fb2);
        fbsw.display();

        //调用功能键的业务方法
        fb1.onClick();
        fb2.onClick();
    }
}


/**
 * FBSettingWindow是“功能键设置”界面类，
 * FunctionButton 充当请求调用者，
 * Command 充当抽象命令类，
 * MinimizeCommand 和 HelpCommand 充当具体命令类，
 * WindowHanlder 和 HelpHandler 充当请求接收者。
 */
public class FBSettingWindow {
    private  String name ; // 窗口名称
    private List<FunctionButton> functionButtonList = new ArrayList<FunctionButton>();
    public FBSettingWindow(String name) {
        this.name  = name;
    }
    public  String getName () {
        return  this.name;
    }

    /**
     * 增加功能键
     * @param functionButton
     */
    public void addFunctionButton(FunctionButton functionButton) {
        this.functionButtonList.add(functionButton);
    }

    // 依次显示功能键
   public  void display() {
       System.out.println("----打印功能键-------------------------------");
       for (FunctionButton functionButton: functionButtonList ) {
            System.out.println(functionButton.getName());
       }
       System.out.println("-----------------------------------");
   }
}


/**
 * 功能按钮类：调用者
 */
class FunctionButton {
    private  String name; // 按钮名称
    private  Command command; // 对应的命令


    public  FunctionButton( String name) {
        this.name = name;
    }
    public  String getName (  ) {
        return  this.name;
    }


    /**
     * 设值注入
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    public  void onClick() {
        System.out.println("单击功能键：" + this.getName());
        if(command == null)
            throw  new NullPointerException("Command is not init");
        this.command.execute();
    }

}


/**
 * 抽象命令类
 */
interface Command {
    void execute(); // 执行具体的命令
}

/**
 * 最小化命令类
 */
class MinimizeCommand implements  Command {
    private  WindowHanlder windowHanlder;
    public  MinimizeCommand() {  // 创建命令对象时，初始化对应的业务对象
        windowHanlder = new WindowHanlder();
    }
    /**
     * 调用接收者的方法
     */
    public void execute() {
        System.out.println("调用接收者");
        windowHanlder.minimize();
    }
}

/**
 * 窗口处理类： 具体的接收者
 */
class WindowHanlder {
    public  void minimize() {
        System.out.println("窗口最小化");
    }
}

/**************************************************************************/
/*扩展时，只需要增加一个命令对象和一个具体的接收者对象*/
/**
 * 帮助命令
 */
class   HelpCommand implements  Command {
    HelpHandler helpHandler ;
    public  HelpCommand() {
        helpHandler = new HelpHandler();
    }
    public void execute() {
        helpHandler.display();
    }
}

/**
 * 帮助文档类
 */
class HelpHandler {
    public void display() {
        System.out.println("打开帮助文档");
    }
}
/**********************************************************************/