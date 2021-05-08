package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.BsDailyAnnounce;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 日常签到表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-05-07
 */
public interface BsDailyAnnounceMapper extends BaseMapper<BsDailyAnnounce> {

    List<BsDailyAnnounce> getListByUserid(String userid);
}
