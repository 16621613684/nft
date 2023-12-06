package com.example.nft.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer id ;

    private String audio;
    private String goodsImg ;
    private String goodsName ;
    private Double price ;
    private String author ;
    private String type ;
    private Integer owner;
    @TableLogic
    @TableField("goodsStatus")
    private Integer goodsStatus = 0 ;


    public Goods(String goodsImg, String goodsName, Double price, String author, String type, Integer goodsStatus) {
        this.goodsImg = goodsImg;
        this.goodsName = goodsName;
        this.price = price;
        this.author = author;
        this.type = type;
        this.goodsStatus = goodsStatus;
    }
    public Goods(Integer id) {
        this.id = id;
    }
}
