package com.gyy.community.mapper;

import com.gyy.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author GYY
 * @date 2019/12/20 20:46
 */
@Mapper
public interface UserMapper {

    /**
     * 通过token查询信息
     * @param token token
     * @return User
     */
    @Select("select * from user where token = #{token}")
    User selectByToken(String token);

    /**
     * 通过id查询信息
     * @param  id id
     * @return user
     */
    @Select("select * from user where id = #{id}")
    User selectById(Integer id);

    /**
     * 通过accountId查询用户信息
     * @param accountId accountId
     * @return 返回用户对象
     */
    @Select("select * from user where account_id = #{accountId}")
    User selectByAccountId(String accountId);

    /**
     * 更新用户
     * @param user 用户信息
     */
    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmtModified},avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);

    /**
     * 插入user信息
     * @param user 用户信息
     */
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
}
