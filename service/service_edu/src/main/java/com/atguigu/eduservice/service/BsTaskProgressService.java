package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.BsTaskProgress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 毕设任务进展表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-14
 */
public interface BsTaskProgressService extends IService<BsTaskProgress> {

    List<BsTaskProgress> queryProgress(String id);
}
