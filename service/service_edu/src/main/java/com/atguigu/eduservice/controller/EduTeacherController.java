package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
        try{ int a=10/0;
        }catch (Exception e){
            //执行自定义异常
            throw new GuliException(200001,"执行了自定义异常。。");
        }

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
    @ApiOperation(value = "分页条件查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(
            @ApiParam(name = "current",value="当前页数",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value="每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name = "TeacherQuery",value="查询条件并封装成对象",required = false)
            @RequestBody(required = false) TeacherQueryVO teacherQueryVO
    ){
        //创建page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper =new QueryWrapper<>();
        //多条件组合查询:类似于mybatis动态sql

       String name=teacherQueryVO.getName();
       Integer level=teacherQueryVO.getLevel();
       String begin=teacherQueryVO.getBegin();
       String end=teacherQueryVO.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
       if(!StringUtils.isEmpty(name)){
           wrapper.like("name",name);

       }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);

        }
        if(!StringUtils.isEmpty(begin)){
           wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.ge("gmt_modified",end);
        }
        //调用方法实现条件查询
        eduTeacherService.page(pageTeacher,wrapper);
        Long total=pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    /*
    *
    * 添加讲师接口*/
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "EduTeacher",value="查询条件并封装成对象",required = true)
            @RequestBody EduTeacher eduTeacher
    ) {
         boolean save=eduTeacherService.save(eduTeacher);
         if(save){
             return R.ok().data("eduTeacher",eduTeacher);
         }else {
             return R.error();
         }
    }
    /*
    *
    *根据讲师id进行查询*/
    @ApiOperation(value = "通过id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(
            @ApiParam(name = "id",value="讲师id",required = true)
            @PathVariable String id
    ) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    /*
    * 讲师修改*/
    @ApiOperation(value = "通过id修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(
            @ApiParam(name = "updateTeacher",value="修改后的讲师对象",required = true)
            @RequestBody
            EduTeacher eduTeacher
    ){
           boolean flag=eduTeacherService.updateById(eduTeacher);
           if(flag){
               return R.ok().data("eduTeacher",eduTeacher);
           }
           else{
               return R.error();
           }
    }






}

