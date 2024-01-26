package com.KunJinKao.controller;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.dto.AddArticleDto;
import com.KunJinKao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 35238
 * @date 2023/8/7 0007 15:21
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    //------------------------------新增博客文章-----------------------------


    //------------------------------新增博客文章-----------------------------

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @PreAuthorize("@ps.hasPermission('content:article:writer')")//权限控制不能让所有人都进来添加文章
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }
}