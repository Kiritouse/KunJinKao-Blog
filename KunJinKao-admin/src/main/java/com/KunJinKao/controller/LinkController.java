package com.KunJinKao.controller;

import com.KunJinKao.domain.entity.Link;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.service.LinkService;
import com.KunJinKao.domain.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 35238
 * @date 2023/8/11 0011 8:46
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    //-----------------------------分页查询友链---------------------------------

    @GetMapping("/list")
    public ResponseResult list(Link link, Integer pageNum, Integer pageSize) {
        PageVo pageVo = linkService.selectLinkPage(link,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }
    //-------------------------------修改友链---------------------------------

    @GetMapping(value = "/{id}")
    //①根据id查询友链
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Link link = linkService.getById(id);
        return ResponseResult.okResult(link);
    }

    @PutMapping("/changeLinkStatus")
    //②修改友链状态
    public ResponseResult changeLinkStatus(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    @PutMapping
    //③修改友链
    public ResponseResult edit(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    //-------------------------------删除友链---------------------------------

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        linkService.removeById(id);
        return ResponseResult.okResult();
    }

}