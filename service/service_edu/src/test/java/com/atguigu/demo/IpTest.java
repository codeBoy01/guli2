package com.atguigu.demo;
import java.net.*;
import java.util.*;
public class IpTest {
    public  static void main(String[] args) {
        try {
//获取所有接口，并放进枚举集合中，然后使用Collections.list()将枚举集合转换为ArrayList集合
            Enumeration enu = NetworkInterface.getNetworkInterfaces();
            ArrayList arr = Collections.list(enu);
            for(Iterator it = arr.iterator();it.hasNext();) {
                NetworkInterface ni = (NetworkInterface) it.next();
                String intName = ni.getName(); //获取接口名
//获取每个接口中的所有ip网络接口集合，因为可能有子接口
                ArrayList inets = Collections.list(ni.getInetAddresses());
                for(Iterator it1 = inets.iterator();it1.hasNext();) {
                    InetAddress inet = (InetAddress) it1.next();
//只筛选ipv4地址，否则会同时得到Ipv6地址
                    if(inet instanceof Inet4Address) {
                        String ip = inet.getHostAddress();
                        System.out.printf("%-10s %-5s %-6s %-15s\n", "InetfaceName:",intName,"| IPv4:",ip);
                    }
                }
            }
        } catch (SocketException s) {
            s.printStackTrace();
        }
    }

}
