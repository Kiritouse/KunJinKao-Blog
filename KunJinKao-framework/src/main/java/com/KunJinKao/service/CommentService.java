package com.KunJinKao.service;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-01-23 09:59:11
 */
public interface CommentService extends IService<Comment> {
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}

