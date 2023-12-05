package com.example.nft.pojo;

import lombok.Data;

@Data
public class BalanceHistory {
    private Integer id ;
    private Integer userId ;
    private Integer orderId ;
    private Double change ;
    private String type ;
    private String time ;
}
