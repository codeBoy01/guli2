package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-19
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //1.根据课程id查询课程里面的所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);

        //2.根据课程id查询课程里面的所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> videoList = videoService.list(wrapperVideo);

        //创建集合，用于最终封装
        List<ChapterVo> finalList = new ArrayList<>();
        //3.遍历查询章节list集合进行封装
        for(int i= 0;i < chapterList.size();i++) {
            EduChapter eduChapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            //把chapterVo放在最终list集合中。
            finalList.add(chapterVo);

            //4.遍历查询小节list集合进行封装
            List<VideoVo> videoVoList = new ArrayList<>();
            for (int m = 0; m < videoList.size(); m++) {
                EduVideo eduVideo = videoList.get(m);
                //判断小节里的chapterId是否等于章节里的chapterId
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return finalList;
    }

    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterId章节id 查询小节表
        //小节表中有数据不删除章节，无数据删除章节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if(count > 0){
            throw new GuliException(20001,"不能删除");
            //能查询出小节，不进行删除
        }
        else {
            int result = baseMapper.deleteById(chapterId);
             return result>0;
        }

    }
}
