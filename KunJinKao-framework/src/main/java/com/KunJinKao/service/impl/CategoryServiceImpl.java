package com.KunJinKao.service.impl;

import com.KunJinKao.domain.entity.Category;
import com.KunJinKao.mapper.CategoryMapper;
import com.KunJinKao.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-01-20 20:46:26
 */
@Service("categoryService")
    public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

