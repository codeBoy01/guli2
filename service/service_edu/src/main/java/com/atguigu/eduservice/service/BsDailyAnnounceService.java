package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.BsDailyAnnounce;
import com.atguigu.eduservice.entity.vo.PieData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 日常签到表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-07
 */
public interface BsDailyAnnounceService extends IService<BsDailyAnnounce> {

    List<BsDailyAnnounce> getListByUserid(String userid);

    List<PieData> getPieData(String userid);
}
