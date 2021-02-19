package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData>{
    //因为这个监听器不能交给Spring管理，需要自己new，不能注入其他对象。
    //不能实现mybatis对数据库的管理
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {

        this.eduSubjectService = eduSubjectService;
    }

    //读取excel内容，一行一行进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw  new GuliException(20001,"文件数据为空");
        }
        //一行一行进行读取，每次读取有两个值，第一个值为一级分类，第二个值为二级分类
        //判断一级分类
        EduSubject existOneEduSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(existOneEduSubject == null)
        {
            //没有相同一级目录，进行添加。
               existOneEduSubject=new EduSubject();
               existOneEduSubject.setParentId("0");
               existOneEduSubject.setTitle(subjectData.getOneSubjectName());
               eduSubjectService.save(existOneEduSubject);
        }
        //获取一级分类的id值
        String pid = existOneEduSubject.getId();
        //判断二级分类
        EduSubject existTwoEduSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjcetName(), pid);
        if(existTwoEduSubject == null)
        {
            existTwoEduSubject=new EduSubject();
            existTwoEduSubject.setParentId(pid);
            existTwoEduSubject.setTitle(subjectData.getTwoSubjcetName());
            eduSubjectService.save(existTwoEduSubject);
        }
    }
        //判断一级目录不能重复添加
        private EduSubject existOneSubject(EduSubjectService subjectService,String name){
            QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
            wrapper.eq("title",name);
            wrapper.eq("parent_id","0");
            EduSubject oneSubject=subjectService.getOne(wrapper);
            return oneSubject;
        }

        //判断二级目录不能重复添加
        private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
         QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
         wrapper.eq("title",name);
         wrapper.eq("parent_id",pid);
         EduSubject twoSubject=subjectService.getOne(wrapper);
         return twoSubject;
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
