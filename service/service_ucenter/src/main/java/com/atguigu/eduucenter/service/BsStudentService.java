package com.atguigu.eduucenter.service;

import com.atguigu.eduucenter.entity.BsUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 毕设用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-03
 */
public interface BsStudentService extends IService<BsUser> {

    String login(BsUser bsUser);
}
