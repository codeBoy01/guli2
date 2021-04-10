package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsMeeting;
import com.atguigu.eduservice.entity.BsTask;
import com.atguigu.eduservice.mapper.BsMeetingMapper;
import com.atguigu.eduservice.service.BsMeetingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 毕设会议表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-07
 */
@Service
public class BsMeetingServiceImpl extends ServiceImpl<BsMeetingMapper, BsMeeting> implements BsMeetingService {

    @Override
    public void finishTask(String id) {
        BsMeeting bsMeeting = new BsMeeting();
        bsMeeting.setId(id);
        bsMeeting.setStatus(true);
        bsMeeting.setGmtFinish(new Date());
        Integer rows = baseMapper.updateById(bsMeeting);

    }

}
