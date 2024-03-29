package com.KunJinKao.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2024-01-20 11:01:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article")
@Accessors(chain = true)//链式操作,set方法由void返回值变成返回对象本身
public class Article {
    @TableId
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;

    @TableField(exist = false)//代表这个字段在数据表中实际上是不存在的,避免MyBabitsPlus在查询的时候出错
    private String categoryName;

    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
//将redis中的浏览量更新到mysql数据库中
    public Article(Long id,long viewCount){
        this.id = id;
        this.viewCount = viewCount;
    }

}

