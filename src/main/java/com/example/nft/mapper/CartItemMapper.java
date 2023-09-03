package com.example.nft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

}
