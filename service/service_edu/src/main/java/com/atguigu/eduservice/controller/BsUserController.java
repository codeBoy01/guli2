package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsMeeting;
import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.MeetingQueryVO;
import com.atguigu.eduservice.entity.vo.StudentQueryVO;
import com.atguigu.eduservice.service.BsTaskService;
import com.atguigu.eduservice.service.BsUserService;
import com.atguigu.eduservice.util.IpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 毕设用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-24
 */
@Api(description = "用户管理操作")
@RestController
@RequestMapping("/eduservice/bs-user")
@CrossOrigin
public class BsUserController {
    @Autowired
    private BsUserService bsUserService;

    /*
     * 获取ip
     */
    @ApiOperation(value = "获取IP")
    @RequestMapping(value = "/getIp", method = RequestMethod.POST)
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }

    //获取管理员id
    @GetMapping("/getId/{username}")
    public R getIdByUsername(@PathVariable String username){
        String userId = bsUserService.queryIdByName(username);
        return R.ok().data("userId",userId);
    }
   //获取管理员信息
    @GetMapping("/getInfo/{username}")
    public R getInfoByUsername(@PathVariable String username){
        BsUser bsUser = bsUserService.queryUser(username);
        return R.ok().data("manager",bsUser);
    }


    //修改管理员信息
    /*
     * 讲师修改*/
    @ApiOperation(value = "通过id修改管理员信息")
    @PostMapping("updateManagerInfo")
    public R updateTeacher(
            @ApiParam(name = "updateManager",value="修改后的管理员对象",required = true)
            @RequestBody BsUser bsUser
    ){
        boolean flag=bsUserService.updateById(bsUser);
        if(flag){
            return R.ok().data("bsUser",bsUser);
        }
        else{
            return R.error();
        }
    }
    /*
     * 多条件组合查询学生带分页
     */
    @ApiOperation(value = "分页条件查询学生")
    @PostMapping("pageStudentCondition/{current}/{limit}")
    public R pageStudentCondition(
            @ApiParam(name = "current",value="当前页数",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value="每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name = "TaskQuery",value="查询条件并封装成对象",required = false)
            @RequestBody(required = false) StudentQueryVO studentQueryVO
    ){
        //创建page对象
        Page<BsUser> pageUser = new Page<>(current,limit);
        //构建条件
        QueryWrapper<BsUser> wrapper = new QueryWrapper<>();
        //多条件组合查询:类似于mybatis动态sql

        String labName = studentQueryVO.getLabName();
        String username = studentQueryVO.getUsername();
        String userclass = studentQueryVO.getUserclass();
        String begin = studentQueryVO.getBegin();
        String end = studentQueryVO.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(labName)){
            wrapper.like("lab_name",labName);

        }
        if(!StringUtils.isEmpty(username)){
            wrapper.like("username",username);

        }
        if(!StringUtils.isEmpty(userclass)){
            wrapper.like("userclass",userclass);

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
        bsUserService.page(pageUser,wrapper);
        Long total=pageUser.getTotal();//总记录数
        List<BsUser> records = pageUser.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    //添加学生
    //获取上传过来的文件，把文件内容读取出来
    @ApiOperation(value = "读取excel文件")
    @PostMapping("/addStudent")
    public R addStudent(MultipartFile file){
        //上传过来excel文件
        bsUserService.saveStudent(file,bsUserService);
        return R.ok();
    }

    //获取学生用户
    @GetMapping("/getStudents")
    public R getStudents(){
        List<BsUser> studentList = new ArrayList<>();
        studentList = bsUserService.getStudentList();
        return R.ok().data("studentList",studentList);
    }

    /*
     * 逻辑删除
     * */
    @ApiOperation(value="根据id逻辑删除学生")
    @DeleteMapping("deleteStudent/{id}")
    public R removeById(
            @ApiParam(name = "id",value="学生id",required = true)
            @PathVariable String id)
    {
        boolean flag = bsUserService.removeById(id);
        if(flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    //获取全部用户
    @GetMapping("/getAll")
    public R getAll(){
        return R.ok().data("list",bsUserService.list(null));
    }


     //    TODO
    //添加用户
    @PostMapping("/addUser")
    public R addUser(){

      return R.ok();
    }

}

