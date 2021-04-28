package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.entity.excel.StudentData;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.listener.StudentExcelListener;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.BsUserMapper;
import com.atguigu.eduservice.service.BsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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

    @Override
    public void saveStudent(MultipartFile file, BsUserService bsUserService) {
        try{
            //文件输入流
            InputStream in=file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, StudentData.class,new StudentExcelListener(bsUserService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String queryPasswordByUsername(String username) {
     return bsUserMapper.queryPasswordByUsername(username);
    }

    @Override
    public BsUser queryUserByAccount(String token) {
        return bsUserMapper.queryUserByAccount(token);
    }

    @Override
    public String queryIdByName(String username) {
        return bsUserMapper.queryIdByName(username);
    }

    @Override
    public BsUser queryById(String id) {
        return bsUserMapper.queryById(id);
    }

    @Override
    public BsUser queryUser(String username) {
        return bsUserMapper.queruUserByUsername(username);
    }
}
