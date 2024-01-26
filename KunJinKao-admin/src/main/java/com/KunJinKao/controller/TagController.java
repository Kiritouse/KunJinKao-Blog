package com.KunJinKao.controller;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.dto.AddTagDto;
import com.KunJinKao.domain.dto.TagListDto;
import com.KunJinKao.domain.entity.Tag;
import com.KunJinKao.service.TagService;
import com.KunJinKao.domain.vo.PageVo;
import com.KunJinKao.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 35238
 * @date 2023/8/2 0002 21:27
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    //TagService是我们在huanf-framework工程的接口
    private TagService tagService;

    //查询标签列表
    @GetMapping("/list")
    //ResponseResult是我们在huanf-framework工程的实体类
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        //pageTagList是我们在huanf-framework工程写的方法
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }
    @PostMapping
    public ResponseResult add(@RequestBody AddTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }
}