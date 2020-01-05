package com.gyy.community.service;

import com.gyy.community.mapper.UserMapper;
import com.gyy.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author GYY
 * @date 2020/1/5 20:50
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        //通过accountId查询老的用户信息
        User dbUser = userMapper.selectByAccountId(user.getAccountId());
        //新用户则添加到数据库中
        if (dbUser == null) {
            dbUser.setGmtCreate(System.currentTimeMillis());
            dbUser.setGmtModified(user.getGmtCreate());
            userMapper.insert(dbUser);
        }else {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            userMapper.update(dbUser);
        }
    }
}
