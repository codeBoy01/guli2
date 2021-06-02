package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.BsMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 毕设用户表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-05-08
 */
public interface BsMessageMapper extends BaseMapper<BsMessage> {

    List<BsMessage> getList(String userid);

    List<BsMessage> getByFlag();

    List<BsMessage> getAnnouncement();
}
