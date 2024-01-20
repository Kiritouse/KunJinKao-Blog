package com.KunJinKao.controller;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Article;
import com.KunJinKao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {

        ResponseResult result =  articleService.hotArticleList();
        return result;
    }
}
