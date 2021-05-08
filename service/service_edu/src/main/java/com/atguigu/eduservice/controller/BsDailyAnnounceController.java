package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsDailyAnnounce;
import com.atguigu.eduservice.entity.BsMeeting;
import com.atguigu.eduservice.service.BsDailyAnnounceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日常签到表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-07
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/bs-daily-announce")
public class BsDailyAnnounceController {

    @Autowired
    private BsDailyAnnounceService bsDailyAnnounceService;

    /*通过userid来获取个人签到信息*/
    @GetMapping("getAnnounceInfo/{userid}")
    public R getInfo(@PathVariable String userid ){
        List<BsDailyAnnounce> announceList = bsDailyAnnounceService.getListByUserid(userid);
        System.out.println(announceList.get(0).toString());
        return R.ok().data("announceList",announceList);
    }
    /*
     *
     * 添加会议接口*/
    @ApiOperation(value = "签到")
    @GetMapping("addAnnounce/{userid}")
    public R addAnnounce(@PathVariable String userid ) {
        List<BsDailyAnnounce> announceList = bsDailyAnnounceService.getListByUserid(userid);
        for(int i=0;i<announceList.size();i++){
            announceList.get(i).getAnnounceDate().getYear();
        }
     //如果查出有签到记录，则更新
        Date date = new Date();

  // 无签到记录则增加
return R.ok();
    }

}

