package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try{
            //文件输入流
            InputStream in=file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //课程分类列表
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1.查询所有的一级分类
        QueryWrapper<EduSubject> wrapperOne=new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList=baseMapper.selectList(wrapperOne);


        //2.查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo=new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList=baseMapper.selectList(wrapperTwo);


        //3.创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList=new ArrayList<>();


        //4.封装一级分类
        //将查询出来的所有一级分类list集合遍历，得到每个一级分类对象值
        //EduSubject->OneSubject
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject=oneSubjectList.get(i);
            OneSubject oneSubject=new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);
            //5.封装二级分类
            //在一级分类循环遍历查询到的二级分类
            //创建children的list集合
            List<TwoSubject> twoFinalSubjectList=new ArrayList<>();
            for(int m=0;m<twoSubjectList.size();m++)
            {
                EduSubject tSubject=twoSubjectList.get(m);
                //判断二级分类的parent_id和一级分类的id是否相等
                if(tSubject.getParentId().equals(eduSubject.getId()))
                {
                TwoSubject twoSubject=new TwoSubject();
                BeanUtils.copyProperties(tSubject,twoSubject);
                twoFinalSubjectList.add(twoSubject);
                }
            }
            //把二级分类加入一级分类中的children中
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }
}
