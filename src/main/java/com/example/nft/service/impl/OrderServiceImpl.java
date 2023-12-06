package com.example.nft.service.impl;

import com.example.nft.mapper.*;
import com.example.nft.pojo.BalanceHistory;
import com.example.nft.pojo.CartItem;
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
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BalanceMapper balanceMapper;

    @Override
    public void checkout(OrderBean orderBean,Integer userId) {
        orderMapper.insert(orderBean);
        //把购物车的项加入订单项
        orderMapper.addOrderItem(userId);

        List<CartItem> cartItems = cartMapper.myCart(userId);
        //遍历每一个藏品
        for (CartItem cartItem:cartItems){
            Goods goods = goodsMapper.selectById(cartItem.getGoodsId());
            Double price = goods.getPrice();
            //变更余额
            userMapper.addBalanceById(price,goods.getOwner());
            userMapper.minusBalanceById(price,userId);
            //交易记录
            balanceMapper.insert(new BalanceHistory(0,userId,goods.getOwner(),goods.getId(),orderBean.getId(),price,orderBean.getOrderDate()));
            //变更商品状态
            goodsMapper.setGoodsStatusById(goods.getId());
            //变更商品拥有者
            goodsMapper.setOwnerById(userId,goods.getId());
        }
        //订单项绑定订单号
        orderMapper.bindOrder(orderBean.getId());
        //加入已拥有的藏品
        //orderMapper.addToOwn(userId);
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


}
