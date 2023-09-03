package com.example.nft.service;

import com.example.nft.pojo.User;

public interface UserService {
    public User login(User user);
    public void register(User user);
}
