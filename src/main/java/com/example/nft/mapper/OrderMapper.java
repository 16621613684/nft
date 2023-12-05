package com.example.nft.mapper;
import java.util.List;

import com.example.nft.pojo.Goods;
import org.apache.ibatis.annotations.*;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.OrderBean;

@Mapper
public interface OrderMapper extends BaseMapper<OrderBean> {
   @Insert("insert into own(id,goodsId,userId) (select id,goodsId,userId from cart_item where userId=#{userId})")
    public void addToOwn(Integer userId);

    List<OrderBean> selectAllByOrderUser(@Param("orderUser") Integer orderUser);

    @Insert("INSERT INTO order_item (goods)(SELECT goodsId FROM cart_item WHERE userId=#{userId})")
    void addOrderItem(Integer userId);
    @Update("UPDATE order_item SET orderBean =#{id} WHERE orderBean IS NULL")
    void bindOrder(Integer id);
    @Select("SELECT *FROM goods WHERE id IN (SELECT goods FROM order_item where orderBean=#{id})")
    List<Goods> getGoods(Integer id);

    @Delete("delete from own where goodsId=#{goodsId} and userId=#{userId}")
    void deleteFromOwn(Integer goodsId,Integer userId);


    @Update("UPDATE goods SET goodsStatus =1 WHERE id IN (SELECT goodsId FROM cart_item WHERE userId=#{id})")
    void setGoodsStatusById(Integer id);

}
