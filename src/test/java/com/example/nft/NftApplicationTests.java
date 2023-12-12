package com.example.nft;

import com.example.nft.mapper.*;
import com.example.nft.pojo.*;
import com.example.nft.service.CartService;
import com.example.nft.service.GoodsService;
import com.example.nft.utils.Pinyin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    AuctionMapper auctionMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void user(){
    }

    @Test
    public void s(){
        goodsMapper.setGoodsStatus();
    }}
