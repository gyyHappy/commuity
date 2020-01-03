package com.gyy.community.dto;

import com.gyy.community.model.User;
import lombok.Data;

/**
 * @author GYY
 * @date 2020/1/2 17:58
 */
@Data
public class QuestionDTO {

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

    /**
     * github 用户信息
     */
    private User user;
}
