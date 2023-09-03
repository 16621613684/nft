package com.example.nft.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.nft.mapper.CartItemMapper;
import com.example.nft.mapper.CartMapper;
import com.example.nft.mapper.GoodsMapper;
import com.example.nft.mapper.UserMapper;
import com.example.nft.pojo.Cart;
import com.example.nft.pojo.CartItem;
import com.example.nft.pojo.Goods;
import com.example.nft.pojo.User;
import com.example.nft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public void addCart(CartItem cartItem) {
        Integer goodsId = cartItem.getGoodsId();
        //goodsMapper.setGoodsStatusById(goodsId);
        //将商品状态码设为1
        Integer userId = cartItem.getUserBeanId();
        cartMapper.addCart(goodsId,userId);
    }

    @Override
    public Cart myCart(Integer userId) {
        List<CartItem> cartItems = cartMapper.myCart(userId);
        Double sum=0.0;
        Integer count=0;
        for (CartItem cartItem:cartItems) {
            Goods goods = goodsMapper.selectById(cartItem.getGoodsId());
            cartItem.setGoods(goods);
           /* User user=userMapper.selectUserById(cartItem.getUserBeanId());
            cartItem.setUser(user);*/
            sum+=goods.getPrice();
            count++;
        }
        Cart cart = new Cart(cartItems, sum, count);
        return cart;
    }

    @Override
    public void deleteById(Integer cartItemId,Integer userId){
        cartMapper.deleteById(cartItemId,userId);
        cartMapper.updateStatus(cartItemId);
    }

    @Override
    public void deleteAll(Integer userId) {
        goodsMapper.clearCart(userId);
        cartMapper.deleteAll(userId);
    }

    @Override
    public void clearCart(Integer userId) {
        cartMapper.deleteAll(userId);
    }

    @Override
    public CartItem getById(Integer goodsId, Integer userId) {
        return cartMapper.getById(goodsId,userId);
    }


}
