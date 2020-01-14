package com.gyy.community.dto;

import com.gyy.community.model.User;
import lombok.Data;

/**
 * @author GYY
 * @date 2020/1/12 13:25
 */
@Data
public class CommentDTO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.id
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.parent_id
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.type
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.commentator
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private Long commentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gmt_create
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gmt_modified
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.like_count
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private Long likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.content
     *
     * @mbg.generated Sat Jan 11 10:38:38 CST 2020
     */
    private String content;

    private User user;

    private Integer commentCount;
}
