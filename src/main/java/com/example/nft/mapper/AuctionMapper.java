package com.example.nft.mapper;

import com.example.nft.pojo.Auction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author User
* @description 针对表【auction】的数据库操作Mapper
* @createDate 2023-12-12 15:53:36
* @Entity com.example.nft.pojo.Auction
*/
@Mapper
public interface AuctionMapper extends BaseMapper<Auction> {

}




