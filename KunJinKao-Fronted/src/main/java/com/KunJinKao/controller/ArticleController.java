package com.KunJinKao.controller;

import com.KunJinKao.domain.entity.Article;
import com.KunJinKao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController
{
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")

    public List<Article> test(){
        return articleService.list();
    }
}
