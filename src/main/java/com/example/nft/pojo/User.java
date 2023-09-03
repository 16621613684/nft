package com.example.nft.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class User {
    private Integer id ;

    public void setId(Integer id) {
        this.id = id;
    }

    private String username;
    private String password;

    private List<Goods> goodsList;
    private  Cart cart;
    private List<OrderBean> orderList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
