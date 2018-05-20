package com.yixiu.designpattern.create._5prototype;

import com.yixiu.designpattern.util.HttpUtil;

/**
 * 浏览器
 * 仅演示最简单的内容
 */
public class Explorer {

    // 根据网址打开一个网页
    public  Tab open(String url) {
        String html = HttpUtil.doGet(url);
        Tab tab = new Tab();
        tab.setUrl( url);
        tab.setHtml(html);
        tab.setLocation(0);
        return  tab;
    }

    // 获取下一行的内容，默认100个字符为一行
    public String getCurrentLine(Tab tab) {
        int lineLength = 100;
        int start = tab.getLocation();
        int end = start + lineLength;
        String html = tab.getHtml();
        String oneLine = html.substring(start, end);

        return oneLine;
    }

}


/**
 * 一个标签页
 * 复制标签页可以使用原型模式
 */
class Tab implements  Cloneable
{
    private  String url;// 网址
    private  String html; // 网页
    private  int location; // 当前显示位置


    /**
     * 克隆一个标签页
     * @return
     */
    public Tab clone() {

        Tab tab = new Tab();
        tab.url = this.url;
        tab.html = this.html;
        tab.location = this.location;
        return  tab;

        //return super.clone();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
