package com.KunJinKao.controller;

import com.KunJinKao.annotation.SystemLog;
import com.KunJinKao.constants.SystemConstants;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.dto.AddCommentDto;
import com.KunJinKao.domain.entity.Comment;
import com.KunJinKao.service.CommentService;
import com.KunJinKao.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/comment")
@Api(tags="评论相关接口",description = "暂定")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    //告诉要从body种获取参数
    /*@PostMapping
    public ResponseResult addComment(@RequestBody Comment comment ){
        return commentService.addComment(comment);
    }*/
    @PostMapping
//在文章的评论区发送评论。ResponseResult是我们在huanf-framework工程写的类
    @SystemLog(businessName = "在文章评论区发送评论")//接口描述，用于'日志记录'功能
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
        //把addCommentDto类转换为Comment类类型。BeanCopyUtils是我们在huanf-framework工程写的工具类，可以转换类的类型
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }

    @GetMapping("linkCommentList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);

    }
}
