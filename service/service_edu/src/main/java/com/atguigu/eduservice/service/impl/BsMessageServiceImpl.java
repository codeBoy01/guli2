package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsMessage;
import com.atguigu.eduservice.mapper.BsMessageMapper;
import com.atguigu.eduservice.service.BsMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 毕设用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-08
 */
@Service
public class BsMessageServiceImpl extends ServiceImpl<BsMessageMapper, BsMessage> implements BsMessageService {

    @Autowired
    private BsMessageMapper bsMessageMapper;
    @Override
    public List<BsMessage> getList(String userid) {
        return bsMessageMapper.getList(userid);


    }

    @Override
    public List<BsMessage> getByFlag() {
        return bsMessageMapper.getByFlag();
    }

    @Override
    public List<BsMessage> getAnnouncement() {
        return bsMessageMapper.getAnnouncement();
    }
}
