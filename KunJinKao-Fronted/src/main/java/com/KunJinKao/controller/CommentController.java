package com.KunJinKao.controller;

import com.KunJinKao.constants.SystemConstants;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Comment;
import com.KunJinKao.service.CommentService;
import io.swagger.annotations.Api;
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
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment ){
        return commentService.addComment(comment);
    }

    @GetMapping("linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);

    }
}
