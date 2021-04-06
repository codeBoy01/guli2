package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQueryVO {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value="头衔 1高级讲师 2首席教师")
    private Integer level;

    @ApiModelProperty(value = "发布开始时间",example = "2020-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "发布结束时间",example = "2020-01-01 10:10:10")
    private String end;


}
