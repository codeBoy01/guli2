package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.BsMeetingSign;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 日常签到表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
public interface BsMeetingSignMapper extends BaseMapper<BsMeetingSign> {

    String getNum(String meetingid);

    int getNum2(String meetingid);
    int getNum3(String meetingid);
}
