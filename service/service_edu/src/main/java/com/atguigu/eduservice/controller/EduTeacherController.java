package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
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
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
     /*
     * 返回所有教师json数据
     * */
    @GetMapping("allTeachers")
    public List<EduTeacher> list(){
        return eduTeacherService.list(null);
    }
    /*
    * 逻辑删除
    * */
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return eduTeacherService.removeById(id);
    }

}

