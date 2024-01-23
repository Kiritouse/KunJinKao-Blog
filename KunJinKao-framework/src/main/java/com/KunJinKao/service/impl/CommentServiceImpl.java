package com.KunJinKao.service.impl;

import com.KunJinKao.constants.SystemConstants;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Comment;
import com.KunJinKao.domain.vo.CommentVo;
import com.KunJinKao.domain.vo.PageVo;
import com.KunJinKao.enums.AppHttpCodeEnum;
import com.KunJinKao.exception.SystemException;
import com.KunJinKao.mapper.CommentMapper;
import com.KunJinKao.service.CommentService;
import com.KunJinKao.service.UserService;
import com.KunJinKao.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-01-23 09:59:13
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论,根评论root为-1
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //查询对应文章的子评论.对articleId进行判断,友链没有文章id,所以这里查询的是文章评论的情况
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, -1);

        //评论类型
        queryWrapper.eq(Comment::getType,commentType);
        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论集合,并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));

    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //在Utils里有mybabits的自动字段填充
        //评论内容不难为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据根评论id查询所对应的子评论的集合
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(comments);
        return commentVoList;
    }

    private List<CommentVo> toCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        //手动处理数据库中没有的字段
        //遍历vo集合
        //通过createBy查询用户信息并赋值
        for (CommentVo commentVo : commentVos) {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户信息并赋值
            //如果toCommentUserId不为-1才进行查询
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }

        }
        return commentVos;
    }
}

