package com.example.nft.service.impl;

import com.example.nft.mapper.UserMapper;
import com.example.nft.pojo.User;
import com.example.nft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User login(User user){
        return userMapper.login(user);
    }

    public void register(User user)
    {
    if (userMapper.login(user)==null)
         userMapper.addUser(user);
    }


}
