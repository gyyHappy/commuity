package com.gyy.community.dto;

import lombok.Data;

/**
 * 问题类
 * @author gyy
 */
@Data
public class Question {

    /**
     * id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 修改时间
     */
    private Long gmtModified;
    /**
     * 创建者id
     */
    private Integer creator;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 阅读数
     */
    private Integer viewCount;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 标签
     */
    private String tag;

}
