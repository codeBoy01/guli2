package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsMessage;
import com.atguigu.eduservice.entity.BsTask;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.atguigu.eduservice.service.BsMeetingService;
import com.atguigu.eduservice.service.BsMessageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 毕设用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-08
 */
@RestController
@RequestMapping("/eduservice/bs-message")
@CrossOrigin
public class BsMessageController {
    @Autowired
    private BsMessageService bsMessageService;

    @GetMapping("getList/{userid}")
    public R getMessageList(@PathVariable String userid){
        List<BsMessage> bsMessageList = bsMessageService.getList(userid);
        return R.ok().data("list",bsMessageList);
    }

    /*获取公告列表*/
    @GetMapping("getAnnounce/{current}/{limit}")
    public R getAnnounce(@ApiParam(name = "current",value="当前页数",required = true) @PathVariable Long current,
                         @ApiParam(name = "limit",value="每页记录数",required = true) @PathVariable Long limit)
    {
        Page<BsMessage> bsMessagePage=new Page<>(current,limit);
        //构建条件
        QueryWrapper<BsMessage> wrapper =new QueryWrapper<>();
        //多条件组合查询:类似于mybatis动态sql

//        String name = bsMessage.getContent();
        //判断条件值是否为空，如果不为空拼接条件
//        if(!StringUtils.isEmpty(name)){
//            wrapper.like("content",name);
//        }
        wrapper.eq("is_announce",true);
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询
        bsMessageService.page(bsMessagePage,wrapper);
        Long total=bsMessagePage.getTotal();//总记录数
        List<BsMessage> records = bsMessagePage.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    /*
     * 多条件组合查询带分页
     */
    @ApiOperation(value = "分页条件查询消息")
    @PostMapping("pageMessageCondition/{current}/{limit}")
    public R pageMessageCondition(
            @ApiParam(name = "current",value="当前页数",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value="每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name = "TeacherQuery",value="查询条件并封装成对象",required = false)
            @RequestBody(required = false) BsMessage bsMessage
    ){
        System.out.println(bsMessage.toString());
        //创建page对象
        Page<BsMessage> bsMessagePage=new Page<>(current,limit);
        //构建条件
        QueryWrapper<BsMessage> wrapper =new QueryWrapper<>();
        //多条件组合查询:类似于mybatis动态sql

//        String name = bsMessage.getContent();
        String userid = bsMessage.getUserid();
        boolean flag = bsMessage.getIsAnnounce();
        //判断条件值是否为空，如果不为空拼接条件
//        if(!StringUtils.isEmpty(name)){
//            wrapper.like("content",name);
//        }
        wrapper.eq("userid",userid);
        wrapper.eq("is_announce",flag);
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询
        bsMessageService.page(bsMessagePage,wrapper);
        Long total=bsMessagePage.getTotal();//总记录数
        List<BsMessage> records = bsMessagePage.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    /*
    * 发送消息*/
    @PostMapping("addMessage")
    public R addMessage(
        @ApiParam(name = "BsMessage",value="查询条件并封装成对象",required = true)
        @RequestBody BsMessage bsMessage
    ) {
        boolean save = bsMessageService.save(bsMessage);
        if (save) {
            return R.ok().data("bsMessage", bsMessage);
        } else {
            return R.error();
        }
    }
    @PostMapping("addMessageByBatch")
    public R addMessageList(@RequestBody BsMessage bsMessage){
        String title = bsMessage.getTitle();
        String content = bsMessage.getContent();
        String userids = bsMessage.getUserid();
        String ids[] = userids.split(",");
        for(int i=0;i<ids.length;i++){
            BsMessage bsMessage2 = new BsMessage();
            bsMessage2.setTitle(title);
            bsMessage2.setContent(content);
            bsMessage2.setUserid(ids[i]);
            boolean save = bsMessageService.save(bsMessage2);
        }
        return R.ok();
    }


    /*获取公告栏前4条（显示在首页）*/
    @GetMapping("getAnnouncement")
    public R getAnnouncement(){
        List<BsMessage> messageList = new ArrayList<>();
        messageList = bsMessageService.getAnnouncement();
        return R.ok().data("messageList",messageList);
    }



}

