package com.KunJinKao.service.impl;

import com.KunJinKao.domain.entity.Article;
import com.KunJinKao.mapper.ArticleMapper;
import com.KunJinKao.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl <ArticleMapper,Article> implements ArticleService {
}
