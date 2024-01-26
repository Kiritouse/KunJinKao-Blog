package com.KunJinKao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Tag;
import com.KunJinKao.domain.dto.TagListDto;
import com.KunJinKao.domain.vo.PageVo;

/**
 * @author 35238
 * @date 2023/8/2 0002 21:14
 */

public interface TagService extends IService<Tag> {
    //查询标签列表
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}