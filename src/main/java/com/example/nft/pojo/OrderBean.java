package com.example.nft.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("orderbean")
public class OrderBean {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    private String orderNo ;
    private String orderDate;
    private Integer orderUser ;
    private Double orderMoney ;
    private  Integer goodsCount;
    private Integer orderStatus;
}
