package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsDailyAnnounce;
import com.atguigu.eduservice.entity.vo.PieData;
import com.atguigu.eduservice.mapper.BsDailyAnnounceMapper;
import com.atguigu.eduservice.service.BsDailyAnnounceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日常签到表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-07
 */
@Service
public class BsDailyAnnounceServiceImpl extends ServiceImpl<BsDailyAnnounceMapper, BsDailyAnnounce> implements BsDailyAnnounceService {

    @Autowired
    private BsDailyAnnounceMapper bsDailyAnnounceMapper;
    @Override
    public List<BsDailyAnnounce> getListByUserid(String userid) {
      return bsDailyAnnounceMapper.getListByUserid(userid);
    }

    @Override
    public List<PieData> getPieData(String userid) {
        Date date = new Date();
        Integer month = date.getMonth()+1;
        String month2;
        if(month/10 == 0) {
            month2 = "0"+String.valueOf(month)+"%";
        }else {
            month2 = String.valueOf(month)+"%";
        }
        //已签到的天数
        Integer value1 = bsDailyAnnounceMapper.getCount1(userid,month2);
        //请假天数
        Integer value2 = bsDailyAnnounceMapper.getCount2(userid,month2) ;
        //未签到天数
        Integer value3 = date.getDate()-value1-value2;
        List<PieData> list = new ArrayList<>();
        PieData pieData1 = new PieData();
        pieData1.setName("已签到次数"+String.valueOf(value1)+"次");
        pieData1.setValue(value1);
        list.add(pieData1);
        PieData pieData2 = new PieData();
        pieData2.setName("已请假次数"+String.valueOf(value2)+"次");
        pieData2.setValue(value2);
        list.add(pieData2);
        PieData pieData3 = new PieData();
        pieData3.setName("未签到次数"+String.valueOf(value3)+"次");
        pieData3.setValue(value3);
        list.add(pieData3);
        System.out.println(list);
        return list;
    }
}
