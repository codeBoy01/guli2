package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskQueryVO {
    @ApiModelProperty(value = "任务标题，模糊查询")
    private String taskName;

    @ApiModelProperty(value = "发布人id")
    private String sendid;

    @ApiModelProperty(value = "接收人id")
    private String receid;

    @ApiModelProperty(value = "任务状态，0为进行中，1为中止")
    private Boolean taskStatus;

    @ApiModelProperty(value = "查询发布时间",example = "2020-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询完成时间",example = "2020-01-01 10:10:10")
    private String end;

}
