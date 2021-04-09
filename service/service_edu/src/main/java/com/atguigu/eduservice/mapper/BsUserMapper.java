package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.BsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 毕设用户表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-03-24
 */
@Mapper
public interface BsUserMapper extends BaseMapper<BsUser> {
    public String queryNameById(String id);

}
