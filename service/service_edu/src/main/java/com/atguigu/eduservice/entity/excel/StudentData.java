package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentData {
    //实验室名称
    @ExcelProperty(index=0)
    private String labName;

   //学生姓名
    @ExcelProperty(index=1)
    private String username;

    //学生账号
    @ExcelProperty(index=2)
    private String account;

    //班级
    @ExcelProperty(index=3)
    private String userclass;

}
