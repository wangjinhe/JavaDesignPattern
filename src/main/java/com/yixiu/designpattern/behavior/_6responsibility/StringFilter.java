package com.yixiu.designpattern.behavior._6responsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过责任链实现多个字符过滤器
 * https://www.cnblogs.com/ysw-go/p/5432921.html
 */
public class StringFilter {

    public static void main(String args[]) {

        Request request = new Request();
        request.setRequestStr("<html>你好啊，\\，有没有. 黄色啊 </html>");

        FilterChain chain = new FilterChain();
        chain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter()).addFilter(new SpecialCharFilter());
        chain.doFilter(request,chain);

        System.out.println(request.getRequestStr());




    }
}


/**
 * 请求字符串
 */
class Request{
    private  String requestStr;
    public void setRequestStr(String requestStr){
        this.requestStr = requestStr;
    }
    public  String getRequestStr() {
        return  this.requestStr;
    }
}

/**
 * 字符串过滤器
 */
interface IFilter {
    /**
     * 将请求使用过滤器链过滤一遍
     * @param request 请求字符串
     * @param chain 过滤器链，多个请求
     */
    void doFilter(Request request, FilterChain chain ) ;
}

/**
 * 过滤器链，保存所有的过滤器
 * 它控制着责任的执行顺序
 */
class FilterChain  implements  IFilter {
    private List<IFilter> filterList = new ArrayList<IFilter>(); // 保存所有的过滤器
    private  int index  = 0; // 保存当前过滤器的序号

    /**
     * 添加一个过滤器,添加完成后返回本身，可以继续添加
     * @param filter
     * @return
     */
    public FilterChain addFilter(IFilter filter) {
        filterList.add(filter);
        return  this;
    }

    /**
     * 实现过滤功能：挨个使用过滤器过滤请求字符串
     * @param request 请求字符串
     * @param chain 过滤器链，多个请求
     */
    public void doFilter(Request request, FilterChain chain) {
        if(index == filterList.size()) // 如果已经执行到头了，就返回
            return;
        IFilter filter = filterList.get(index) ; // 获取当前的过滤器
        index ++; // 指向下一个
        filter.doFilter(request,chain); // 执行当前的过滤器
    }
}

/**
 * html过滤器，替换掉所有的Html标签
 */
class HtmlFilter implements  IFilter {
    public void doFilter(Request request, FilterChain chain) {
        // 过滤html标签
        String str = request.getRequestStr().replaceAll("<","[").replaceAll(">","]");
        request.setRequestStr(str);
        // 把控制权交给链，让链执行下一个过滤器
        chain.doFilter(request,chain);
    }
}

/**
 * 敏感词的过滤器
 */
class  SensitiveFilter implements  IFilter {
    public void doFilter(Request request, FilterChain chain) {
        // 过滤敏感词，一般是配置在数据库中的
        String str = request.getRequestStr().replaceAll("黄色","").replaceAll("三级","");
        request.setRequestStr(str);
        // 把控制权交给链，让链执行下一个过滤器
        chain.doFilter(request,chain);
    }
}

/**
 * 过滤特殊字符
 */
class SpecialCharFilter implements IFilter {
    public void doFilter(Request request, FilterChain chain) {
        // 特殊字符，比如 \  ！
        // 注意： replaceAll的第1个参数是正则表达式，如果要替换\,正则需要转义为\\,然后在java中，需要再加两个\转义
        String str = request.getRequestStr().replaceAll("\\\\","").replaceAll("!","");
        request.setRequestStr(str);
        // 把控制权交给链，让链执行下一个过滤器
        chain.doFilter(request,chain);
    }
}