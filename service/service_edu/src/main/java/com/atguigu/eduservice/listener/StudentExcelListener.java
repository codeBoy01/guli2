package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.BsUser;
import com.atguigu.eduservice.entity.excel.StudentData;
import com.atguigu.eduservice.service.BsUserService;
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
        String labName = studentData.getLabName();
        String username = studentData.getUsername();
        String account = studentData.getAccount();
        String userclass = studentData.getUserclass();
        BsUser bsUser = new BsUser();
        bsUser.setLabName(labName)
                .setUsername(username)
                .setAccount(account)
                .setUserclass(userclass)
                .setPassword("000000")
                .setAvatar("https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        try{
            bsUserService.save(bsUser);
        } catch (Exception e){
            throw new GuliException(20001,"账号名存在重复，请重新输入");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //读取excel内容，一行一行进行读取

}
