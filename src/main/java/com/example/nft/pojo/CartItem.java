package com.example.nft.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("cart_item")
public class CartItem {
    private Integer id ;
    private Integer goodsId ;
    private Integer userBeanId ;

    private Goods goods;
    private User user;
    public CartItem(Integer goodsId, Integer userBeanId) {
        this.goodsId = goodsId;
        this.userBeanId = userBeanId;
    }

    public CartItem(Integer id, Integer goodsId, Integer userBeanId) {
        this.id = id;
        this.goodsId = goodsId;
        this.userBeanId = userBeanId;
    }
}
