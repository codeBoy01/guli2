package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsMeeting;
import com.atguigu.eduservice.entity.BsTask;
import com.atguigu.eduservice.entity.vo.MeetingQueryVO;
import com.atguigu.eduservice.entity.vo.TaskQueryVO;
import com.atguigu.eduservice.service.BsMeetingService;
import com.atguigu.eduservice.service.BsTaskService;
import com.atguigu.eduservice.service.BsUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 毕设会议表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-07
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/bs-meeting")
@Api(description = "会议管理")
public class BsMeetingController {
    @Autowired
    private BsMeetingService bsMeetingService;
    @Autowired
    private BsUserService bsUserService;

    /*
     * 多条件组合查询任务带分页
     */

    @ApiOperation(value = "分页条件查询会议")
    @PostMapping("pageMeetingCondition/{current}/{limit}")
    public R pageTaskCondition(
            @ApiParam(name = "current",value="当前页数",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value="每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name = "TaskQuery",value="查询条件并封装成对象",required = false)
            @RequestBody(required = false) MeetingQueryVO meetingQueryVO
    ){
        //创建page对象
        Page<BsMeeting> pageMeeting = new Page<>(current,limit);
        //构建条件
        QueryWrapper<BsMeeting> wrapper = new QueryWrapper<>();
        //多条件组合查询:类似于mybatis动态sql

        String meetingName=  meetingQueryVO.getMeetingName();
        String meetingLeaderName = meetingQueryVO.getMeetingLeaderName();
        Boolean status = meetingQueryVO.getStatus();
        String begin = meetingQueryVO.getBegin();
        String end = meetingQueryVO.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(meetingName)){
            wrapper.like("meeting_name",meetingName);

        }
        if(!StringUtils.isEmpty(meetingLeaderName)){
            wrapper.like("meeting_leader_name",meetingLeaderName);

        }
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);

        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询
        bsMeetingService.page(pageMeeting,wrapper);
        Long total=pageMeeting.getTotal();//总记录数
        List<BsMeeting> records = pageMeeting.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    /*
     *
     * 添加会议接口*/
    @ApiOperation(value = "添加会议")
    @PostMapping("addMeeting")
    public R addMeeting(
            @ApiParam(name = "BsMeeting",value="会议对象",required = true)
            @RequestBody BsMeeting bsMeeting
    ) {

        boolean save=bsMeetingService.save(bsMeeting);
        if(save){
            return R.ok().data("bsMeeting",bsMeeting);
        }else {
            return R.error();
        }
    }

    /*
     * 逻辑删除
     * */
    @ApiOperation(value="根据id逻辑删除会议")
    @DeleteMapping("deleteMeeting/{id}")
    public R removeById(
            @ApiParam(name = "id",value="会议id",required = true)
            @PathVariable String id)
    {
        boolean flag = bsMeetingService.removeById(id);
        if(flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    /*
     * 通过id获取姓名*/
    @ApiOperation(value = "根据id查询name")
    @GetMapping("/queryNamesByIds/{users}")
    public R queryNameById(@PathVariable String users){
        String ids[] = users.split(",");
        List<String> userNamesList = new ArrayList<>();
        for(int i = 0;i < ids.length;i++){
            userNamesList.add(bsUserService.queryNameById(ids[i]));
        }
        String userNames = userNamesList.get(0);
        for(int i=1;i<userNamesList.size();i++)
        {
            userNames=userNames+","+userNamesList.get(i);
        }
        return R.ok().data("userNames",userNames);
    }

    /*
     * 完成任务*/
    @ApiOperation(value="根据id完成会议")
    @PostMapping("finishMeeting/{id}")
    public R finishById(
            @ApiParam(name = "id",value="会议id",required = true)
            @PathVariable String id)
    {
        bsMeetingService.finishTask(id);
        return R.ok();
    }

    /*
     *
     *根据会议id进行查询*/
    @ApiOperation(value = "通过id查询")
    @GetMapping("getMeeting/{id}")
    public R getMeetingById(
            @ApiParam(name = "id",value="会议id",required = true)
            @PathVariable String id
    ) {
        BsMeeting bsMeeting = bsMeetingService.getById(id);
        return R.ok().data("bsMeeting",bsMeeting);
    }

    /*
     * 会议修改*/
    @ApiOperation(value = "通过id修改会议")
    @PostMapping("updateMeeting")
    public R updateMeeting(
            @ApiParam(name = "updateMeeting",value="修改后的会议对象",required = true)
            @RequestBody
                    BsMeeting bsMeeting
    ){
        boolean flag=bsMeetingService.updateById(bsMeeting);
        if(flag){
            return R.ok().data("bsMeeting",bsMeeting);
        }
        else{
            return R.error();
        }
    }




}

