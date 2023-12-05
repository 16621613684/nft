package com.example.nft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods>{
    @Select("select * from goods where goodsStatus =0")
     List<Goods> getAllGoods();
    @Update("update goods set goodsStatus =1 where id = #{id}")
     void setGoodsStatusById(Integer id);

    @Update("UPDATE goods SET goodsStatus =0 ,price =#{price} WHERE id = #{id}")
     void resetGoodsById(Integer id,Double price);

    @Select("select * from goods where id = #{id}")
    Goods selectById(Integer id);

     Set<Goods> searchGoods(String mohu);
     Set<Goods> searchGoodsByAuthor(String mohu);
     Set<Goods> searchGoodsByType(String mohu);
    @Update("UPDATE goods SET goodsStatus =0 WHERE id IN(SELECT goodsId FROM cart_item WHERE userId=#{userId})")
     void clearCart(Integer userId);

     void setGoodsStatus();

    @Select("SELECT MIN(price) FROM goods WHERE `type`=#{type} AND goodsStatus =0")
     Integer getFloor(String type);
    @Select("SELECT SUM(price) FROM goods WHERE `type`=#{type} AND goodsStatus =0")
    Integer getTotal(String type);

    void setOwnerById(Integer ownerId,Integer goodsId);

    List<Goods> getAllByOwner(Integer id);

    String getGoodsImgById(Integer id);
}
