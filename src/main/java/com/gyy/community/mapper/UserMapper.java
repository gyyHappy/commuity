package com.gyy.community.mapper;

import com.gyy.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author GYY
 * @date 2019/12/20 20:46
 */
@Mapper
public interface UserMapper {

    /**
     * 插入user信息
     * @param user 用户信息
     */
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    /**
     * 通过token查询信息
     * @param token token
     */
    @Select("select * from user where token = #{token}")
    User selectByToken(String token);
}
