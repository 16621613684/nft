package com.example.nft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceHistory {
    private Integer id ;
    private Integer buyerId ;
    private Integer sellerId ;
    private Integer goodsId ;
    private Integer orderId ;
    private Double change ;
    private String time ;
}
