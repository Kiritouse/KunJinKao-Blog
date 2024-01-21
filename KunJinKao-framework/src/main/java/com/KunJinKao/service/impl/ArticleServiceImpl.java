package com.KunJinKao.service.impl;

import com.KunJinKao.constants.SystemConstants;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Article;
import com.KunJinKao.domain.entity.Category;
import com.KunJinKao.domain.vo.ArticleListVo;
import com.KunJinKao.domain.vo.HotArticleVo;
import com.KunJinKao.domain.vo.PageVo;
import com.KunJinKao.mapper.ArticleMapper;
import com.KunJinKao.service.ArticleService;
import com.KunJinKao.service.CategoryService;
import com.KunJinKao.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Lazy//防止循环依赖
    @Autowired
    private CategoryService categoryService;
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章,封装成一个ResponseResult对象返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();//封装查询条件
        //必须是已经发布的文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);//查寻表中的status字段，和0进行比较
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询10条消息
        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();//获取查询结果
        //bean拷贝,将Article对象转换成HotArticleVo对象
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for(Article article:articles)
//        {
//            HotArticleVo  vo= new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId,就要查询的和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);

        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //如果是置顶的文章,即对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);


        List<Article> articles = page.getRecords();
        //查询categoryName
        articles=articles.stream()
                .map(article -> {
                    //获取分类id,查询分类信息,获取分类名称
                    //把分类名称设置给article
                    return article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                })
                .collect(Collectors.toList());



        //封装查询结果,封装成VO
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());


        return ResponseResult.okResult(pageVo);
    }

}
