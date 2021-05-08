package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsTask;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TaskQueryVO;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.atguigu.eduservice.service.BsTaskService;
import com.atguigu.eduservice.service.BsUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 实验室任务表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/eduservice/bs-task")
@CrossOrigin
@Api(description = "任务管理")
public class BsTaskController {
    @Autowired
    private BsTaskService bsTaskService;


    @ApiOperation(value = "获取全部任务信息")
    @GetMapping("getAllTask")
    public R getAllTask(){

        List<BsTask> list = bsTaskService.list(null);
        return R.ok().data("items",list);

    }

    /*
     *
     * 添加任务接口*/
    @ApiOperation(value = "添加任务")
    @PostMapping("addTask")
    public R addTeacher(
            @ApiParam(name = "BsTask",value="查询条件并封装成对象",required = true)
            @RequestBody BsTask bsTask
    ) {
        boolean save=bsTaskService.save(bsTask);
        if(save){
            return R.ok().data("bsTask",bsTask);
        }else {
            return R.error();
        }
    }
    /*
     * 多条件组合查询任务带分页
     */

    @ApiOperation(value = "分页条件查询任务")
    @PostMapping("pageTaskCondition/{current}/{limit}")
    public R pageTaskCondition(
            @ApiParam(name = "current",value="当前页数",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value="每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name = "TaskQuery",value="查询条件并封装成对象",required = false)
            @RequestBody(required = false) TaskQueryVO taskQueryVO
    ){
        //创建page对象
        Page<BsTask> pageTask = new Page<>(current,limit);
        //构建条件
        QueryWrapper<BsTask> wrapper = new QueryWrapper<>();
        //多条件组合查询:类似于mybatis动态sql

        String taskName=  taskQueryVO.getTaskName();
        Boolean taskStatus = taskQueryVO.getTaskStatus();
        String begin=taskQueryVO.getBegin();
        String end=taskQueryVO.getEnd();
        String sendId = taskQueryVO.getSendid();
        String receId = taskQueryVO.getReceid();
        System.out.println(taskQueryVO.toString());
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(taskName)){
            wrapper.like("task_name",taskName);
        }
        if(!StringUtils.isEmpty(sendId)){
            System.out.println(11);
            wrapper.eq("sendid",sendId);
        }
        if(!StringUtils.isEmpty(receId)){
            wrapper.eq("reid",receId);
        }
        if(!StringUtils.isEmpty(taskStatus)){
            wrapper.eq("task_status",taskStatus);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_finish",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询
       bsTaskService.page(pageTask,wrapper);
        Long total=pageTask.getTotal();//总记录数
        List<BsTask> records = pageTask.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    /*
     * 逻辑删除
     * */
    @ApiOperation(value="根据id逻辑删除任务")
    @DeleteMapping("deleteTask/{id}")
    public R removeById(
            @ApiParam(name = "id",value="任务id",required = true)
            @PathVariable String id)
    {
        boolean flag = bsTaskService.removeById(id);
        if(flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }
   /*
   * 完成任务*/
    @ApiOperation(value="根据id完成任务")
    @PostMapping("finishTask/{id}")
    public R finishById(
            @ApiParam(name = "id",value="任务id",required = true)
            @PathVariable String id)
    {
         bsTaskService.finishTask(id);
         return R.ok();
    }

    /*
     * 任务修改*/
    @ApiOperation(value = "通过id修改任务")
    @PostMapping("updateTask")
    public R updateTask(
            @ApiParam(name = "updateTask",value="修改后的任务对象",required = true)
            @RequestBody
            BsTask bsTask
    ){
        boolean flag=bsTaskService.updateById(bsTask);
        if(flag){
            return R.ok().data("bsTask",bsTask);
        }
        else{
            return R.error();
        }
    }

    /*
     *
     *根据任务id进行查询*/
    @ApiOperation(value = "通过id查询任务")
    @GetMapping("getTask/{id}")
    public R getTaskById(
            @ApiParam(name = "id",value="任务id",required = true)
            @PathVariable String id
    ) {
        BsTask bsTask = bsTaskService.getById(id);
        return R.ok().data("bsTask",bsTask);
    }



}

