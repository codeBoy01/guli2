package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.BsMeetingSign;
import com.atguigu.eduservice.entity.vo.PieData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 日常签到表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
public interface BsMeetingSignService extends IService<BsMeetingSign> {

    List<PieData> getSignInfo(String meetingid);
}
