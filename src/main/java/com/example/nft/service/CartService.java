package com.example.nft.service;

import com.example.nft.pojo.Cart;
import com.example.nft.pojo.CartItem;

public interface CartService {

    public void addCart(CartItem cartItem);

    public Cart myCart(Integer userId);

    public void deleteById(Integer cartItemId,Integer userId);

    public void deleteAll(Integer userId);

    public void clearCart(Integer userId);

    CartItem getById(Integer goodsId,Integer userId);
}
