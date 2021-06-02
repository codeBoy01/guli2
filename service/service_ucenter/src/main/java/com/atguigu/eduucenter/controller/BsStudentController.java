package com.atguigu.eduucenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduucenter.entity.BsUser;
import com.atguigu.eduucenter.service.BsStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 毕设用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-03
 */
@RestController
@CrossOrigin
@RequestMapping("/eduucenter/bs-user")
@Api(description = "学生登录管理")
public class BsStudentController {
    @Autowired
    private BsStudentService bsUserService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody BsUser bsUser){
        String token = bsUserService.login(bsUser);
        return R.ok().data("token",token);
    }
    //根据token获取用户信息
    @GetMapping("getStudentInfo")
    public R getStudentInfo(HttpServletRequest request){
        String studentId = JwtUtils.getMemberIdByJwtToken(request);
        //根据用户id查询信息
        BsUser bsUser = bsUserService.getById(studentId);
        System.out.println(bsUser);
        return R.ok().data("studentInfo",bsUser);
    }
    //修改管理员信息
    @ApiOperation(value = "通过id修改学生本人信息")
    @PostMapping("updateUserInfo")
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

}

