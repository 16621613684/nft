package com.example.nft.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class Cart {
    private List<CartItem> cartItemList;     //购物车中购物车项的集合 , 这个Map集合中的key是Book的id
    private Double totalMoney;                     //购物车的总金额

    public Cart() {
    }

    public Cart(List<CartItem> cartItemList, Double totalMoney, Integer totalCount) {
        this.cartItemList = cartItemList;
        this.totalMoney = totalMoney;
        this.totalCount = totalCount;
    }

    private Integer totalCount;                    //购物车中购物项的数量
}
