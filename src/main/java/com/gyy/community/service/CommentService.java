package com.gyy.community.service;

import com.gyy.community.enums.CommentEnum;
import com.gyy.community.exception.CustomizeErrorCode;
import com.gyy.community.exception.CustomizeException;
import com.gyy.community.mapper.CommentMapper;
import com.gyy.community.mapper.QuestionExtMapper;
import com.gyy.community.mapper.QuestionMapper;
import com.gyy.community.model.Comment;
import com.gyy.community.model.Question;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author GYY
 * @date 2020/1/10 21:27
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentEnum.isExit(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType().equals(CommentEnum.COMMENT.getType())){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
