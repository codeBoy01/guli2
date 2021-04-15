package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(description = "讲师登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决跨域问题
public class EduLoginController {
    //login
    @ApiOperation(value = "讲师登录")
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");

    }

    //info
    @ApiOperation(value = "获取讲师信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]")
                .data("name", "damin").data("avatar", "https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
    }
}
