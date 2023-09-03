package com.example.nft.service.impl;

import com.example.nft.mapper.OrderMapper;
import com.example.nft.pojo.Goods;
import com.example.nft.pojo.OrderBean;
import com.example.nft.service.CartService;
import com.example.nft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CartService cartService;
    @Override
    public void checkout(OrderBean orderBean,Integer userId) {
        orderMapper.insert(orderBean);
        orderMapper.addOrderItem(userId);
        orderMapper.setGoodsStatusById(userId);
        orderMapper.bindOrder(orderBean.getId());
        orderMapper.addToOwn(userId);
        cartService.clearCart(userId);
    }

    @Override
    public List<OrderBean> showOrder(Integer userId) {

        return orderMapper.selectAllByOrderUser(userId);
    }

    @Override
    public List<Goods> getGoods(Integer id) {
        return orderMapper.getGoods(id);
    }

    @Override
    public OrderBean getOrderById(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void deleteFromOwn(Integer goodsId, Integer userId) {
        orderMapper.deleteFromOwn(goodsId,userId);
    }

    @Override
    public List<Goods> getOwnGoods(Integer userId) {
        return orderMapper.getOwnGoods(userId);
    }


}
