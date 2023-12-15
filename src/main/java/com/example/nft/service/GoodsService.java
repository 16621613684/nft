package com.example.nft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.nft.pojo.Auction;
import com.example.nft.pojo.Goods;

import java.util.List;
import java.util.Set;

public interface GoodsService  extends IService<Goods>{
    public List<Goods> getAllGoods();
    public Set<Goods> searchGoods(String mohu);
    public Set<Goods> searchGoodsByType(String mohu);

    public List<Goods> getAllByPrice(Integer way);

    public void resell(Integer goodsId,Double price,Integer userId);

    public Integer getFloor(String type);

    public Integer getTotal(String type);

    public void add(Goods good);

    public List<Goods> getAllByOwner(Integer id);

}
