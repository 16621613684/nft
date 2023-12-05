package com.example.nft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceVO {
    private Integer buyerId;
    private Integer sellerId;
    private String buyerName;
    private String sellerName;
    private Integer goodsId ;
    private String goodsImg ;
    private Integer orderId ;
    private String orderNo ;
    private Double change ;
    private String time ;
}
