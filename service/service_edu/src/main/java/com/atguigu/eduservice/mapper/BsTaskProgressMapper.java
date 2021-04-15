package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.BsTaskProgress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 毕设任务进展表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-04-14
 */
public interface BsTaskProgressMapper extends BaseMapper<BsTaskProgress> {
    List<BsTaskProgress> queryById(String id);

}
