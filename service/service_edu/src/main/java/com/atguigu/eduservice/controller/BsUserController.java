package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.service.BsTaskService;
import com.atguigu.eduservice.service.BsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(description = "用户登录管理操作")
@RestController
@RequestMapping("/eduservice/bs-user")
@CrossOrigin
public class BsUserController {
    @Autowired
    private BsUserService bsUserService;

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
    /*
    * 通过id获取姓名*/
    @ApiOperation(value = "根据id查询name")
    @GetMapping("/queryNameById")
    public R queryNameById(String users){
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

}

