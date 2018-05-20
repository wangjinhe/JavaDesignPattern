package com.yixiu.designpattern.create._4builder;

import javax.swing.*;


public class Builder {

    public static void main(String[] args) {

        // 创建一个中文建议框
        ISuggestPanelBuilder suggestPanelBuilder = new ChineseSuggestPanelBuilder();
        Director director = new Director(suggestPanelBuilder);
        JPanel panel = director.buildPanel();
        JFrame frame = new JFrame();
        frame.setTitle("感谢您的建议");
        frame.add(panel);
        frame.setBounds(5, 5, 1000, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


        // 创建英文建议框
        suggestPanelBuilder = new EnglishSuggestPanelBuilder();
        director = new Director(suggestPanelBuilder);
        panel = director.buildPanel();
        JFrame frame2 = new JFrame();
        frame2.setTitle("Suggestion");
        frame2.add(panel);
        frame2.setBounds(5, 5, 1000, 500);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setVisible(true);

    }
}

/**
 * 用户提建议的弹框，一个产品，它需要不同的表示形式
 */
class SuggestPanel  extends  JPanel {
    JLabel labelTip; //提示语
    JTextArea textSuggest; // 用户填写的建议内容
    JButton buttonSubmit; // 提交按钮
}

/**
 * 抽象的生成器
 */
interface  ISuggestPanelBuilder
{
    void buildLabel();
    void buildText();
    void buildButton();
    SuggestPanel getPanel();
}

/**
 * 中文生成器：部件表示为中文
 */
class ChineseSuggestPanelBuilder implements  ISuggestPanelBuilder
{
    // 生成器中有产品，控制产品的表现形式
    private  SuggestPanel suggestPanel;
    public  ChineseSuggestPanelBuilder() {
        this.suggestPanel = new SuggestPanel();// 初始化一个panel
    }

    public void buildLabel() {
        this.suggestPanel.labelTip = new JLabel("请输入您的建议：");
    }

    public void buildText() {
        this.suggestPanel.textSuggest = new JTextArea();
    }

    public void buildButton() {
        this.suggestPanel.buttonSubmit = new JButton("提交");
    }


    public SuggestPanel getPanel() {
        // 注意：此处需要将3个组件添加到panel中，才可以显示

        // 注意：设置为自定义布局，否则设置子控件位置没用
        this.suggestPanel.setLayout(null);

        this.suggestPanel.add(this.suggestPanel.labelTip);
        // 注意：setBounds = setLocation + setSize 同时设置位置和宽高
        this.suggestPanel.labelTip.setBounds(10,10,1000,30);


        this.suggestPanel.add(this.suggestPanel.textSuggest);
        this.suggestPanel.textSuggest.setLocation(10,50);
        this.suggestPanel.textSuggest.setSize(1000,300);

        this.suggestPanel.buttonSubmit.setBounds(10,355,100,50);
        this.suggestPanel.add(this.suggestPanel.buttonSubmit);

        return  suggestPanel;
    }
}

/**
 * 英语生成器：组件表示为英文
 */
class EnglishSuggestPanelBuilder implements  ISuggestPanelBuilder
{
    private  SuggestPanel suggestPanel;
    public  EnglishSuggestPanelBuilder() {
        this.suggestPanel = new SuggestPanel();// 初始化一个panel
    }

    public void buildLabel() {
        this.suggestPanel.labelTip = new JLabel("Please enter your advice:");
    }

    public void buildText() {
        this.suggestPanel.textSuggest = new JTextArea();
    }

    public void buildButton() {
        this.suggestPanel.buttonSubmit = new JButton("Submit");
    }

    public SuggestPanel getPanel() {
        // 注意：此处需要将3个组件添加到panel中，才可以显示
        // 注意：设置为自定义布局，否则设置子控件位置没用
        this.suggestPanel.setLayout(null);

        this.suggestPanel.add(this.suggestPanel.labelTip);
        // 注意：setBounds = setLocation + setSize 同时设置位置和宽高
        this.suggestPanel.labelTip.setBounds(10,10,1000,30);


        this.suggestPanel.add(this.suggestPanel.textSuggest);
        this.suggestPanel.textSuggest.setLocation(10,50);
        this.suggestPanel.textSuggest.setSize(1000,300);

        this.suggestPanel.buttonSubmit.setBounds(10,355,100,50);
        this.suggestPanel.add(this.suggestPanel.buttonSubmit);

        return  suggestPanel;
    }
}

/**
 * 指挥官，负责根据不同的生成器，生产不同表现形式的产品
 */
class Director {
    // 指挥官中有生成器，控制产品的显示
    private  ISuggestPanelBuilder iSuggestPanelBuilder;
    public  Director(ISuggestPanelBuilder builder) {
        this.iSuggestPanelBuilder = builder;
    }

    public JPanel buildPanel() {
        this.iSuggestPanelBuilder.buildLabel();
        this.iSuggestPanelBuilder.buildText();
        this.iSuggestPanelBuilder.buildButton();
        JPanel panel = this.iSuggestPanelBuilder.getPanel();
        return  panel;
    }


}
