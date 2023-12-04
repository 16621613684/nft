package com.example.nft.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.nft.mapper.UserMapper;
import com.example.nft.pojo.User;
import com.example.nft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User login(User user) {
        return userMapper.login(user);
    }

    public void register(User user) {
        if (userMapper.login(user) == null) {
            if (StringUtils.isNotBlank(user.getUsername())) {
                userMapper.addUser(user);
                return;
            }
            if (StringUtils.isNotBlank(user.getPhone())) {
                user.setUsername("user" + user.getPhone().substring(user.getPhone().length() - 4));
                userMapper.addUserByPhone(user);
                return;
            }
            if (StringUtils.isNotBlank(user.getEmail())) {
                user.setUsername("user" + user.getEmail().substring(0, 4));
                userMapper.addUserByEmail(user);
                return;
            }
        }
    }


}
