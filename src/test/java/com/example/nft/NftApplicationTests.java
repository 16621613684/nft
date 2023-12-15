package com.example.nft;

import com.example.nft.mapper.*;
import com.example.nft.pojo.*;
import com.example.nft.service.CartService;
import com.example.nft.service.GoodsService;
import com.example.nft.utils.Pinyin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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
    @Autowired
    AuctionRecordMapper auctionRecordMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void user(){ AuctionRecord record = new AuctionRecord(0, 1, 1, LocalDateTime.now(), 2.0);
        auctionRecordMapper.insert(record);
    }

    @Test
    public void s(){
        String type="一口价";
        System.out.println(type);
        type="拍卖";
        System.out.println(type);
    }}
