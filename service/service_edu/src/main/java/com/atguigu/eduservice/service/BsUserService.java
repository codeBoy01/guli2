package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.BsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 毕设用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-24
 */
public interface BsUserService extends IService<BsUser> {
    String queryNameById(String id);

    List<BsUser> getStudentList();

    void saveStudent(MultipartFile file, BsUserService bsUserService);

    String queryPasswordByUsername(String username);

    BsUser queryUserByAccount(String token);

    String queryIdByName(String username);

    BsUser queryById(String id);

    BsUser queryUser(String username);
}
