package com.example.nft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.BalanceHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BalanceMapper extends BaseMapper<BalanceHistory> {


    int insert(BalanceHistory balanceHistory);
}
