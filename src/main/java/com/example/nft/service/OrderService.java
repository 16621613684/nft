package com.example.nft.service;

import com.example.nft.pojo.Goods;
import com.example.nft.pojo.OrderBean;

import java.util.List;

public interface OrderService {
    void checkout(OrderBean orderBean,Integer userId);
    List<OrderBean> showOrder(Integer userId);
    List<Goods> getGoods(Integer id);
    OrderBean getOrderById(Integer id);
    void deleteFromOwn(Integer goodsId,Integer userId);
}
