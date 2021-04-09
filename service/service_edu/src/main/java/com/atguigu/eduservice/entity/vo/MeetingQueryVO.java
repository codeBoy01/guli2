package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MeetingQueryVO {
    @ApiModelProperty(value = "会议标题")
    private String meetingName;

    @ApiModelProperty(value = "发布人姓名，模糊查询")
    private String meetingLeaderName;

    @ApiModelProperty(value = "任务状态，0为进行中，1为中止")
    private Boolean status;

    @ApiModelProperty(value = "查询发布时间",example = "2020-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询完成时间",example = "2020-01-01 10:10:10")
    private String end;



}
