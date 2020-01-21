package com.gyy.community.mapper;

import com.gyy.community.dto.QuestionQueryDTO;
import com.gyy.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);

    /**
     * 通过标签查找相关问题
     * @param question 问题
     * @return 问题列表
     */
    List<Question> selectByRelated(Question question);


    int countBySearch(QuestionQueryDTO search);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}