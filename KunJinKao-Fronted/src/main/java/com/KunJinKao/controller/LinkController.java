package com.KunJinKao.controller;

import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.service.LinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@Api(tags = "友链相关接口",description = "暂定")
public class LinkController {

    @Autowired
    private LinkService linkService;
    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){

        return linkService.getAllLink();
    }


}
