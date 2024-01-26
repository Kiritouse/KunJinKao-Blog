package com.KunJinKao.service;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Category;
import com.KunJinKao.domain.vo.CategoryVo;
import com.KunJinKao.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-01-20 20:46:24
 */
public interface CategoryService extends IService<Category> {
    //查询文章分类的接口
    ResponseResult getCategoryList();

    //写博客-查询文章分类的接口
    List<CategoryVo> listAllCategory();

    //分页查询分类列表
    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}

