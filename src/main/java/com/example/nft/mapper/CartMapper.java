package com.example.nft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.CartItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper  {
    @Insert("insert into cart_item values(0,#{goodsId},#{userId})")
    public void addCart(Integer goodsId,Integer userId);
    @Select("select * from cart_item where userId=#{userId}")
    public List<CartItem> myCart(Integer userId);
    @Delete("delete  from cart_item where userId=#{userId} and goodsId=#{goodsId}")
    public void deleteById(Integer goodsId,Integer userId);
    @Update("UPDATE goods SET goodsStatus=0 WHERE id =#{goodsId}")
    public void updateStatus(Integer goodsId);
    @Delete("delete from cart_item where userId=#{userId} ")
    public void deleteAll(Integer userId);
   /* @Update("UPDATE goods SET goodsStatus=0")
    public void clearStatus();*/
    @Select("select * from cart_item where userId=#{userId} and goodsId=#{goodsId}")
    CartItem getById(Integer goodsId,Integer userId);
}
