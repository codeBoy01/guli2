package com.atguigu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日常签到表
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BsMeetingSign对象", description="日常签到表")
public class BsMeetingSign implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "签到者id")
    private String userid;

    @ApiModelProperty(value = "会议id")
    private String meetingid;


    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "签到时间")
    private Date announceDate;

    @ApiModelProperty(value = "是否请假")
    private Integer isVacate;

    @ApiModelProperty(value = "请假原因")
    private String vacateReason;


}
