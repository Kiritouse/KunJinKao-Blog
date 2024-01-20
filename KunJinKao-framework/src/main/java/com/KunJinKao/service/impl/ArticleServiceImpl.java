package com.KunJinKao.service.impl;

import com.KunJinKao.constants.SystemConstants;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Article;
import com.KunJinKao.domain.vo.HotArticleVo;
import com.KunJinKao.mapper.ArticleMapper;
import com.KunJinKao.service.ArticleService;
import com.KunJinKao.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl <ArticleMapper,Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章,封装成一个ResponseResult对象返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();//封装查询条件
        //必须是已经发布的文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);//查寻表中的status字段，和0进行比较
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询10条消息
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();//获取查询结果
        //bean拷贝,将Article对象转换成HotArticleVo对象
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for(Article article:articles)
//        {
//            HotArticleVo  vo= new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles,HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }

}
