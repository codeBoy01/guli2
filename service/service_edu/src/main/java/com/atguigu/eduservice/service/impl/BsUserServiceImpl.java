package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.mapper.BsUserMapper;
import com.atguigu.eduservice.service.BsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 毕设用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-24
 */
@Service
public class BsUserServiceImpl extends ServiceImpl<BsUserMapper, BsUser> implements BsUserService {
@Autowired BsUserMapper bsUserMapper;

    @Override
    public String queryNameById(String id) {
        return bsUserMapper.queryNameById(id);
    }

    @Override
    public List<BsUser> getStudentList() {
        return bsUserMapper.getStudentList();
    }
}
