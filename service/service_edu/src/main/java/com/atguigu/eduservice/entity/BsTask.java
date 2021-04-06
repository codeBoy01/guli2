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
 * 实验室任务表
 * </p>
 *
 * @author testjava
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BsTask对象", description="实验室任务表")
public class BsTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "发布人id")
    private String sendid;

    @ApiModelProperty(value = "任务姓名")
    private String taskName;

    @ApiModelProperty(value = "发布人姓名")
    private String sendname;

    @ApiModelProperty(value = "接收人id")
    private String reid;

    @ApiModelProperty(value = "接收人姓名")
    private String recename;

    @ApiModelProperty(value = "任务内容")
    private String content;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "发布时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "完成时间")
    private Date gmtFinish;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "0为进行中，1为中止")
    private Boolean taskStatus;

    @ApiModelProperty(value = "任务进展情况")
    private String taskProgress;

//    @ApiModelProperty(value = "分组")
//    private String group;

}
