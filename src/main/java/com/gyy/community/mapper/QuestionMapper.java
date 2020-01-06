package com.gyy.community.mapper;

import com.gyy.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author GYY
 * @date 2019/12/23 22:21
 */
@Mapper
public interface QuestionMapper {


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

    /**
     * 通过用户id查询问题列表
     * @param userId 用户id
     * @param offset 起始条数
     * @param size 显示条数
     * @return 返回问题列表
     */
    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") int userId,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    /**
     * 通过用户的id查询问题的条数
     * @param userId 用户id
     * @return 返回用户问题列表
     */
    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(int userId);

    /**
     * 通过id查询问题
     * @param id id
     * @return 问题信息
     */
    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);

    /**
     * 更新问题
     * @param question 问题
     */
    @Update("update question set title = #{title},description = #{description},gmt_create = #{gmtCreate},gmt_modified = #{gmtModified},tag = #{tag} where id = #{id} ")
    void update(Question question);

    /**
     * 添加问题
     *
     * @param question 问题
     */
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);
}
