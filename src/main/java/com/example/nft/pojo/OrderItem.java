package com.example.nft.pojo;

import lombok.Data;

@Data
public class OrderItem {
    private Integer id ;
    private Goods goods ;                 //M:1
    private OrderBean orderBean ;       //M:1

}
