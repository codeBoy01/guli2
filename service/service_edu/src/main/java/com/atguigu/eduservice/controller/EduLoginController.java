package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.service.BsUserService;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;


@Api(description = "讲师登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决跨域问题
public class EduLoginController {
    @Autowired
    private BsUserService bsUserService;
    @Autowired
    private RedisTemplate redisTemplate;
    //login
    @ApiOperation(value = "管理员登录")
    @PostMapping("login/{username}/{password}")
    public R login(@PathVariable String username,@PathVariable String password) {
        String password2 = bsUserService.queryPasswordByUsername(username);
        if (password.equals(password2)){

            return R.ok().data("token",username);
        }else {
            return R.error().message("用户名或者密码错误，登录失败");
        }

    }

    //info
    @ApiOperation(value = "获取管理员信息")
    @GetMapping("info")
    public R info(String token) {
        BsUser bsUser = bsUserService.queryUserByAccount(token);
        return R.ok().data("roles", "[超级管理员]")
                .data("name", bsUser.getUsername()).data("avatar",bsUser.getAvatar());
    }

    //退出登录，前端将值清空，后端只需返回信息即可。
    @ApiOperation(value = "退出登录")
    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
