package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-24
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
     /*
     * 1.返回所有教师json数据
     * */
     @ApiOperation(value = "返回所有的讲师列表")
    @GetMapping("allTeachers")
    public R findAllTeachers(){
         List<EduTeacher> list = eduTeacherService.list(null);
         return R.ok().data("items",list);
     }
    /*
    * 2.逻辑删除
    * */
    @ApiOperation(value="根据id逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id",value="讲师id",required = true)
            @PathVariable String id)
    {
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }
    /*
    *分页查询讲师的方法
    */
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R  pageListTeacher(
            @ApiParam(name = "current",value="当前页数",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value="每页记录数",required = true)
            @PathVariable Long limit
    ){
        //创建page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //调用方式实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总记录数
                List<EduTeacher> records=pageTeacher.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    /*
    * 多条件组合查询带分页
    */



}

