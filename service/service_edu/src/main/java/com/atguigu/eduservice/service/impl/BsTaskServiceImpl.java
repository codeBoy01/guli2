package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsTask;
import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.mapper.BsTaskMapper;
import com.atguigu.eduservice.service.BsTaskService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 实验室任务表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-28
 */
@Service
public class BsTaskServiceImpl extends ServiceImpl<BsTaskMapper, BsTask> implements BsTaskService {

    @Override
    public void finishTask(String id) {

        BsTask bsTask = new BsTask();
        bsTask.setId(id);
        bsTask.setTaskStatus(true);
        bsTask.setGmtFinish(new Date());
        Integer rows = baseMapper.updateById(bsTask);
    }
}
