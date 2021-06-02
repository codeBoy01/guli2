package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsDailyAnnounce;
import com.atguigu.eduservice.entity.BsMeeting;
import com.atguigu.eduservice.entity.BsMessage;
import com.atguigu.eduservice.entity.vo.PieData;
import com.atguigu.eduservice.service.BsDailyAnnounceService;
import com.atguigu.eduservice.util.IpUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

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

    @GetMapping("getAnnounceInfo/{userid}")
    public R getInfo(@PathVariable String userid) {
        List<BsDailyAnnounce> announceList = bsDailyAnnounceService.getListByUserid(userid);
        return R.ok().data("announceList", announceList);
    }

    @PostMapping("sign")
    public R addSign(
            @ApiParam(name = "BsMessage", value = "查询条件并封装成对象", required = true)
            @RequestBody BsDailyAnnounce bsDailyAnnounce,
            HttpServletRequest request
    ) {
        String ip = IpUtil.getIpAddr(request);
        System.out.println(ip);
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("66.183.206.81")) {
            try {
                boolean save = bsDailyAnnounceService.save(bsDailyAnnounce);
                return R.ok().data("bsDailyAnnounce", bsDailyAnnounce);
            } catch (Exception e) {
                return R.error().message("签到失败，已经签到或请假");
            }
        } else {
            return R.error().message("签到失败，ip地址不对，请连接实验室wifi");
        }
    }

    @PostMapping("vacate")
    public R addVacate(
            @ApiParam(name = "BsMessage", value = "查询条件并封装成对象", required = true)
            @RequestBody BsDailyAnnounce bsDailyAnnounce,
            HttpServletRequest request
    ) {
        try {
            boolean save = bsDailyAnnounceService.save(bsDailyAnnounce);
            return R.ok().data("bsDailyAnnounce", bsDailyAnnounce);
        } catch (Exception e) {
            return R.error().message("请假失败，已经签到或请假");
        }
    }

    @GetMapping("pieData/{userid}")
    public R getPieData(@PathVariable String userid){

        List<PieData> list = bsDailyAnnounceService.getPieData(userid);
        return R.ok().data("pieData",list);


    }

}

