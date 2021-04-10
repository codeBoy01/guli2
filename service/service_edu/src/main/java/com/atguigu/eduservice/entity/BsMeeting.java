package com.atguigu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 毕设会议表
 * </p>
 *
 * @author testjava
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BsMeeting对象", description="毕设会议表")
public class BsMeeting implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "发起人姓名")
    private String meetingLeaderName;

    @ApiModelProperty(value = "发起人ID")
    private String meetingLeaderId;

    @ApiModelProperty(value = "会议标题")
    private String meetingName;

    @ApiModelProperty(value = "任务内容")
    private String meetingContent;

    @ApiModelProperty(value = "参会人员id(用,分隔)")
    private String meetingPersonId;

    @ApiModelProperty(value = "参会人员name(用,分隔)")
    private String meetingPersonName;

    @ApiModelProperty(value = "会议开始时间")
    private Date meetingStartTime;

    @ApiModelProperty(value = "会议结束时间")
    private Date gmtFinish;

    @ApiModelProperty(value = "会议时长")
    private Integer meetingMinute;

    @ApiModelProperty(value = "会议地点")
    private String meetingPlace;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "0为进行中，1为中止")
    private Boolean status;


}
