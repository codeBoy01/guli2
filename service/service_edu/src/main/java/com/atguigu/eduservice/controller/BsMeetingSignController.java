package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsDailyAnnounce;
import com.atguigu.eduservice.entity.BsMeetingSign;
import com.atguigu.eduservice.entity.vo.PieData;
import com.atguigu.eduservice.service.BsMeetingSignService;
import com.atguigu.eduservice.util.IpUtil;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 日常签到表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/bs-meeting-sign")
public class BsMeetingSignController {
    @Autowired
    private BsMeetingSignService bsMeetingSignService;
    /*签到*/
    @PostMapping("signOrVacate")
    public R addSign(
            @RequestBody BsMeetingSign bsMeetingSign
    ){
            try {
                boolean save = bsMeetingSignService.save(bsMeetingSign);
                return R.ok().data("bsMeetingSign", bsMeetingSign);
            } catch (Exception e) {
                return R.error().message("操作失败,已经签到或者请假");
            }


    }
    @GetMapping("getSignPieData/{meetingid}")
    public R getSingInfo(@PathVariable String meetingid){
        List<PieData> list = bsMeetingSignService.getSignInfo(meetingid);
        return R.ok().data("list",list);
    }



}

