package com.KunJinKao.controller;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Tag;
import com.KunJinKao.domain.dto.AddTagDto;
import com.KunJinKao.domain.dto.TabListDto;
import com.KunJinKao.domain.dto.TagListDto;
import com.KunJinKao.service.TagService;
import com.KunJinKao.utils.BeanCopyUtils;
import com.KunJinKao.domain.vo.PageVo;
import com.KunJinKao.domain.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //-------------------------------新增标签------------------------------------

    @PostMapping
    public ResponseResult add(@RequestBody AddTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    //-------------------------------删除标签------------------------------------

    @DeleteMapping("/{id}")
    //pageTagList是我们在huanf-framework工程写的方法
    public ResponseResult deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

    //-------------------------------修改标签------------------------------------


    @GetMapping("/{id}")
    //①根据标签的id来查询标签
    public ResponseResult getLableById(@PathVariable Long id){
        return tagService.getLableById(id);
    }

    @PutMapping
    //②根据标签的id来修改标签
    public ResponseResult updateById(@RequestBody TagVo tagVo){
        return tagService.myUpdateById(tagVo);
    }


    //---------------------------写博文-查询文章标签的接口---------------------------

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}