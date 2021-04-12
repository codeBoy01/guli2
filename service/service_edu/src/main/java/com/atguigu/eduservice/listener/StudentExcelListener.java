package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.StudentData;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.BsUserService;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;

public class StudentExcelListener extends AnalysisEventListener<StudentData> {
    public BsUserService bsUserService;
    public StudentExcelListener() {
    }

    public StudentExcelListener(BsUserService bsUserService) {

        this.bsUserService = bsUserService;
    }

    @Override
    public void invoke(StudentData studentData, AnalysisContext analysisContext) {
        if(studentData == null){
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
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //读取excel内容，一行一行进行读取

}
