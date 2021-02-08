package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel
{
    public static void main(String[] args) {
//        //实现excel写操作
//        //1.设置写入文件夹地址和excel文件名称
//        String filename="D:\\easyexcel\\write.xlsx";
//        //2.调用easyexcel实现写操作
//        //write方法两个参数，第一个参数是文件路径名称，第二参数是实体类class
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
//         写操作
            String filename="D:\\easyexcel\\write.xlsx";
            EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private  static List<DemoData> getData(){
     List<DemoData> list = new ArrayList<>();
     for(int i=0;i<10;i++){
         DemoData data=new DemoData();
         data.setSno(i);
         data.setName("lucy"+i);
         list.add(data);
     }
     return list;
    }
}
