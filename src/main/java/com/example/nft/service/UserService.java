package com.example.nft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.nft.pojo.User;

public interface UserService extends IService<User> {
    public User login(User user);
    public void register(User user);
}
