package com.KunJinKao.service.impl;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Article;
import com.KunJinKao.mapper.ArticleMapper;
import com.KunJinKao.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl <ArticleMapper,Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章,封装成一个ResponseResult对象返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();//封装查询条件
        //必须是已经发布的文章
        queryWrapper.eq(Article::getStatus,"0");//查寻表中的status字段，和0进行比较
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询10条消息
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);
        List<Article> articleList = page.getRecords();//获取查询结果
        return ResponseResult.okResult(articleList);
    }

}
