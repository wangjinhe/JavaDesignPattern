package com.yixiu.designpattern.create._5prototype;

public class TestExplorer {
    public  static  void main(String [] args)
    {
        Explorer explorer = new Explorer();
        String url = "http://news.qq.com/";
        Tab tab1 = explorer.open(url);
        tab1.setLocation(0);


        Tab tab2 = tab1.clone();

        System.out.println("=========================");
        System.out.println("tab1");
        System.out.println("url=" + tab1.getUrl());
        System.out.println(explorer.getCurrentLine(tab1));

        System.out.println("=========================");
        System.out.println("tab2");
        System.out.println("url=" + tab2.getUrl());
        System.out.println(explorer.getCurrentLine(tab2));

        System.out.println("=========================");
        System.out.println("tab1 == tab2 ? " + tab1.equals(tab2));

    }


}
