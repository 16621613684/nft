package com.example.nft;

import com.example.nft.mapper.CartMapper;
import com.example.nft.mapper.GoodsMapper;
import com.example.nft.mapper.OrderMapper;
import com.example.nft.mapper.UserMapper;
import com.example.nft.pojo.*;
import com.example.nft.service.CartService;
import com.example.nft.service.GoodsService;
import com.example.nft.utils.Pinyin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
class NftApplicationTests {
@Autowired
    GoodsService goodsService;
@Autowired
    CartService cartService;
@Autowired
    CartMapper cartMapper;
@Autowired
    GoodsMapper goodsMapper;
@Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void user(){
        String a = Pinyin.toPinyin("张三");
        System.out.println(a);
    }

    @Test
    public void s(){
        goodsMapper.setGoodsStatus();
    }}
