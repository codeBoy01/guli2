package com.atguigu.eduucenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.eduucenter.entity.BsUser;
import com.atguigu.eduucenter.mapper.BsStudentMapper;
import com.atguigu.eduucenter.service.BsStudentService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 毕设用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-03
 */
@Service
public class BsStudentServiceImpl extends ServiceImpl<BsStudentMapper, BsUser> implements BsStudentService {

    @Override
    public String login(BsUser bsUser) {
       //获取登录用户名和密码
        String account = bsUser.getAccount();
        String password = bsUser.getPassword();
        //用户名和密码为空
        if(StringUtils.isEmpty(bsUser) || StringUtils.isEmpty(password))
        {
            throw new GuliException(20001,"登录失败");
        }
        //判断用户名是否正确
        QueryWrapper<BsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        BsUser bsUser2 = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(bsUser2 == null){
            //没有这个用户名
            throw new GuliException(20001,"不存在此用户");
        }
        //判断密码
        System.out.println(MD5.encrypt(password));
        if(!MD5.encrypt(password).equals(bsUser2.getPassword())){
            throw new GuliException(20001,"密码错误");
        }
        //判断是否被删除、
        if(bsUser2.getIsDeleted() == true){
            throw new GuliException(20001,"登录失败，用户已被移除");
        }
        //登录成功后
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(bsUser2.getId(),bsUser2.getUsername());
        return jwtToken;
    }
}
