package com.gyy.community.mapper;

import com.gyy.community.dto.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author GYY
 * @date 2019/12/23 22:21
 */
@Mapper
public interface QuestionMapper {

    /**
     * 添加问题
     * @param question 问题
     */
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);
}
