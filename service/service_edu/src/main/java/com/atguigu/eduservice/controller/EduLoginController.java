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
        return R.ok().data("token","admin");

    }
    //info
    @ApiOperation(value = "获取讲师信息")
    @GetMapping("info")
    public  R info(){
        return R.ok().data("roles","[admin]")
                .data("name","damin").data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa0.att.hudong.com%2F30%2F29%2F01300000201438121627296084016.jpg&refer=http%3A%2F%2Fa0.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1615169149&t=86ba10e7513ad5aa9f6f509a61a4f34a");
        }
}
