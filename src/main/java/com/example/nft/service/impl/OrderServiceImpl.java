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
        //把购物车的项加入订单项
        orderMapper.addOrderItem(userId);
        //变更商品状态
        orderMapper.setGoodsStatusById(userId);
        //订单项绑定订单号
        orderMapper.bindOrder(orderBean.getId());
        //加入已拥有的藏品
        orderMapper.addToOwn(userId);
        //扣除余额
        //新增余额变更记录
        //清空购物车
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
