package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsDailyAnnounce;
import com.atguigu.eduservice.mapper.BsDailyAnnounceMapper;
import com.atguigu.eduservice.service.BsDailyAnnounceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
