package com.KunJinKao.service;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    //根据id查询文章详情
    ResponseResult getArticleDetail(Long id);

    //根据id从mysql中查询文章
    ResponseResult updateViewCount(Long id);
}
