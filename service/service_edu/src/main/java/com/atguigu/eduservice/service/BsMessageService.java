package com.atguigu.eduservice.service;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 毕设用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-08
 */
public interface BsMessageService extends IService<BsMessage> {

    List<BsMessage> getList(String userid);

    List<BsMessage> getByFlag();

    List<BsMessage> getAnnouncement();
}
