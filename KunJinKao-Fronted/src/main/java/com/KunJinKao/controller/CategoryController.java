package com.KunJinKao.controller;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(tags="分类相关接口",description = "暂定")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @RequestMapping("getCategoryList")
    public ResponseResult getCategoryList(){
      return  categoryService.getCategoryList();


    }
}
