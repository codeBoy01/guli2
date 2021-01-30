package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
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
     * 返回所有教师json数据
     * */
     @ApiOperation(value = "返回所有的讲师列表")
    @GetMapping("allTeachers")

    public List<EduTeacher> list(){

        return eduTeacherService.list(null);
    }
    /*
    * 逻辑删除
    * */
    @ApiOperation(value="根据id逻辑删除讲师")
    @DeleteMapping("{id}")
    public boolean removeById(
            @ApiParam(name = "id",value="讲师id",required = true)
            @PathVariable String id)
    {

        return eduTeacherService.removeById(id);
    }


}

