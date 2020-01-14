package com.gyy.community.mapper;

import com.gyy.community.model.Comment;

/**
 * @author GYY
 * @date 2020/1/14 22:58
 */

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}
