package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentQueryVO {
    @ApiModelProperty(value = "实验室名称")
    private String labName;

    @ApiModelProperty(value = "学生姓名")
    private String username;

    @ApiModelProperty(value = "班级")
    private String userclass;

    @ApiModelProperty(value = "创建开始时间",example = "2020-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "创建结束时间",example = "2020-01-01 10:10:10")
    private String end;
}
