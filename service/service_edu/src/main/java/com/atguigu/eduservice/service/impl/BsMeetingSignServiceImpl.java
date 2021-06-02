package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsMeetingSign;
import com.atguigu.eduservice.entity.vo.PieData;
import com.atguigu.eduservice.mapper.BsMeetingSignMapper;
import com.atguigu.eduservice.service.BsMeetingSignService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 日常签到表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@Service
public class BsMeetingSignServiceImpl extends ServiceImpl<BsMeetingSignMapper, BsMeetingSign> implements BsMeetingSignService {

    @Autowired
    private BsMeetingSignMapper bsMeetingSignMapper;
    @Override
    public List<PieData> getSignInfo(String meetingid) {
        List<PieData> list = new ArrayList<>();
        //参会人数
        String nums = bsMeetingSignMapper.getNum(meetingid);
        String nums2[] = nums.split(",");
        //总人数
        int count1 = nums2.length;
        //签到人数
        PieData pieData1 = new PieData();
        int count2 = bsMeetingSignMapper.getNum2(meetingid);
        pieData1.setValue(count2);
        pieData1.setName("已签到人数"+String.valueOf(count2)+"人次");
        //请假人数
        PieData pieData2 = new PieData();
        int count3 = bsMeetingSignMapper.getNum3(meetingid);
        pieData2.setValue(count3);
        pieData2.setName("已请假人数"+String.valueOf(count3)+"人次");
        //未签到和请假人数
        PieData pieData3 = new PieData();
        int count4 = count1 - count2 - count3;
        pieData3.setValue(count4);
        pieData3.setName("未签到人数"+String.valueOf(count4)+"人次");
        list.add(pieData1);
        list.add(pieData2);
        list.add(pieData3);
        System.out.println(list);
        return list;

    }
}
