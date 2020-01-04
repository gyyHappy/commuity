package com.gyy.community.mapper;

import com.gyy.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author GYY
 * @date 2019/12/23 22:21
 */
@Mapper
public interface QuestionMapper {

    /**
     * 添加问题
     *
     * @param question 问题
     */
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);


    /**
     * 查询问题列表
     *
     * @param offset 当前页
     * @param size   一个页面显示的条数
     * @return Question
     */
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    /**
     * 查询问题条数
     *
     * @return 返回问题条数
     */
    @Select("select count(1) from question")
    Integer count();
}
