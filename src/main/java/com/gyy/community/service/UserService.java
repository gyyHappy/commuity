package com.gyy.community.service;

import com.gyy.community.mapper.UserMapper;
import com.gyy.community.model.User;
import com.gyy.community.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GYY
 * @date 2020/1/5 20:50
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        //通过accountId查询老的用户信息
        List<User> users = userMapper.selectByExample(userExample);
        //新用户则添加到数据库中
        if (users.size() < 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            User dbUser = users.get(0);
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            userMapper.updateByPrimaryKey(dbUser);
        }
    }
}
