package com.yixiu.designpattern.behavior._12observer;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * 天气服务
 */
public class WeatherServiceExample {

    public static void main(String args[]){

        WeatherService weatherService = WeatherService.Instance; // 获取服务
        weatherService.addClient(new ClientAndroidServer());
        weatherService.addClient(new ClientIphoneServer());

        weatherService.updateWeather(new WeatherInfo(System.currentTimeMillis(),"阴天"));
        weatherService.updateWeather(new WeatherInfo(System.currentTimeMillis(),"下雨"));
        weatherService.updateWeather(new WeatherInfo(System.currentTimeMillis(),"晴天"));


    }
}


/**
 * 天气信息
 */
class WeatherInfo {
    private long time; //时间
    private  String weather; // 天气
    public WeatherInfo(long time,String weather) {
        this.time = time;
        this.weather = weather;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return  false;
        WeatherInfo info = (WeatherInfo)obj;
        return  info.time == this.time && info.weather.equals(this.weather);
    }
}


/**
 * 天气服务
 */
interface IWeatherService {
    void addClient(Client client); // 添加客户端
    void removeClient(Client client); // 移除客户端
    void notifyClients(); // 通知其他客户端
    void updateWeather(WeatherInfo weatherInfo); // 更新天气
}

/**
 * 客户端
 */
interface Client {
      void getWeather(WeatherInfo weatherInfo); // 获取天气
}

/**
 * 天气服务
 * 通过枚举实现单例
 */
enum WeatherService implements  IWeatherService {
    Instance; // 单例
    private LinkedList<WeatherInfo> weatherInfos = new LinkedList<WeatherInfo>(); // 天气
    private LinkedHashSet<Client> clients = new LinkedHashSet<Client>(); // 客户端

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void updateWeather(WeatherInfo weatherInfo) {
        if(weatherInfos.size() > 0)
            if(weatherInfos.peek().equals(weatherInfo))  return;

        weatherInfos.push(weatherInfo);  // 此处虽然显示红色，但是运行不报错
       // weatherInfos.add(weatherInfo); // add是顺序添加，peek永远是第1个
        if(clients.size() == 0) return; // 不需要通知
        notifyClients();
    }
    // 通知所有的客户端
    public void notifyClients() {
        if(clients.size() == 0) return;
        if(weatherInfos.size() == 0 ) return;
        WeatherInfo weatherInfo = weatherInfos.peek();
        for (Client client: clients ) {
            client.getWeather(weatherInfo);
        }
    }
}

/**
 * 安卓
 */
class ClientAndroidServer implements  Client {
    private WeatherInfo weatherInfo; // 天气
    private  String name = "安卓客户端";


    public void getWeather(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
        dealMsg();
    }

    public void dealMsg() {
        System.out.println(name + "收到天气消息，time=" + weatherInfo.getTime() + ",天气:" + weatherInfo.getWeather()  +",马上向客户端推送消息");

    }
}

/**
 * iphone
 */
class ClientIphoneServer implements  Client {
    private WeatherInfo weatherInfo; // 天气
    private  String name = "Iphone客户端";


    public void getWeather(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
        dealMsg();
    }

    public void dealMsg() {
        System.out.println(name + "收到天气消息，time=" + weatherInfo.getTime() + ",天气:" + weatherInfo.getWeather()  +",马上向客户端推送消息");

    }
}