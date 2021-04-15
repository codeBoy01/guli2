package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsTaskProgress;
import com.atguigu.eduservice.mapper.BsTaskProgressMapper;
import com.atguigu.eduservice.service.BsTaskProgressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 毕设任务进展表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-14
 */
@Service
public class BsTaskProgressServiceImpl extends ServiceImpl<BsTaskProgressMapper, BsTaskProgress> implements BsTaskProgressService {
    @Autowired
    private BsTaskProgressMapper bsTaskProgressMapper;

    @Override
    public List<BsTaskProgress> queryProgress(String id) {

        return bsTaskProgressMapper.queryById(id);

    }
}
