package com.example.nft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nft.pojo.Auction;
import com.example.nft.service.AuctionService;
import com.example.nft.mapper.AuctionMapper;
import org.springframework.stereotype.Service;

/**
* @author User
* @description 针对表【auction】的数据库操作Service实现
* @createDate 2023-12-12 15:53:36
*/
@Service
public class AuctionServiceImpl extends ServiceImpl<AuctionMapper, Auction>
    implements AuctionService{

}




