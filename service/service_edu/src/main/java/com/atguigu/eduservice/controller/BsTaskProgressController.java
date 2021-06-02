package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.BsTask;
import com.atguigu.eduservice.entity.BsTaskProgress;
import com.atguigu.eduservice.service.BsTaskProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 毕设任务进展表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/eduservice/bs-task-progress")
@CrossOrigin
@Api(description = "任务进展管理")
public class BsTaskProgressController {
    @Autowired
    private BsTaskProgressService bsTaskProgressService;

    @ApiOperation(value = "根据任务id来查询进展")
    @GetMapping("getTaskProgressById/{id}")
    public R getTaskProgressById(
            @ApiParam(name = "id",value="任务id",required = true)
            @PathVariable String id)
    {
        List<BsTaskProgress> bsTaskProgresses = bsTaskProgressService.queryProgress(id);
        return R.ok().data("bsTaskProgress",bsTaskProgresses);
    }
    /*
     *
     * 添加任务接口*/
    @ApiOperation(value = "添加任务进展")
    @PostMapping("addTaskProgress")
    public R addTeacher(
            @RequestBody BsTaskProgress bsTaskProgress
    ) {
        boolean save=bsTaskProgressService.save(bsTaskProgress);
        if(save){
            return R.ok().data("bsTaskProgress",bsTaskProgress);
        }else {
            return R.error();
        }
    }

}

