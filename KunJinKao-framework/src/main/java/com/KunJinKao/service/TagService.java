package com.KunJinKao.service;

import com.KunJinKao.domain.dto.TabListDto;
import com.KunJinKao.domain.dto.TagListDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Tag;
import com.KunJinKao.domain.vo.PageVo;
import com.KunJinKao.domain.vo.TagVo;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/2 0002 21:14
 */

public interface TagService extends IService<Tag> {
    //查询标签列表
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
    //新增标签
    ResponseResult addTag(TabListDto tabListDto);
    //删除标签
    ResponseResult deleteTag(Long id);
    //修改标签-①根据标签的id来查询标签
    ResponseResult getLableById(Long id);
    //修改标签-②根据标签的id来修改标签
    ResponseResult myUpdateById(TagVo tagVo);

    //写博文-查询文章标签的接口
    List<TagVo> listAllTag();
}