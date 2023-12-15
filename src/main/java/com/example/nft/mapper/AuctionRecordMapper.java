package com.example.nft.mapper;

import com.example.nft.pojo.AuctionRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author User
* @description 针对表【auction_record】的数据库操作Mapper
* @createDate 2023-12-13 16:38:15
* @Entity com.example.nft.pojo.auction_record
*/
@Mapper
public interface AuctionRecordMapper extends BaseMapper<AuctionRecord> {

}



