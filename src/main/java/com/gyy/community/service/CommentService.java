package com.gyy.community.service;

import com.gyy.community.exception.CustomizeErrorCode;
import com.gyy.community.exception.CustomizeException;
import com.gyy.community.model.Comment;
import org.springframework.stereotype.Service;

/**
 * @author GYY
 * @date 2020/1/10 21:27
 */
@Service
public class CommentService {
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
