package com.example.nft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nft.mapper.GoodsMapper;
import com.example.nft.pojo.Goods;
import com.example.nft.service.GoodsService;
import com.example.nft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl  implements GoodsService {
    @Autowired
    OrderService orderService;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

    @Override
    public Set<Goods> searchGoods(String mohu) {
        Set<Goods> goods = goodsMapper.searchGoods(mohu);
        Set<Goods> goods1 = goodsMapper.searchGoodsByAuthor(mohu);
        Set<Goods> goods2 = goodsMapper.searchGoodsByType(mohu);

        goods.addAll(goods1);
        goods.addAll(goods2);
        return goods;
    }

    @Override
    public Set<Goods> searchGoodsByType(String mohu) {
        return goodsMapper.searchGoodsByType(mohu);
    }

    @Override
    public List<Goods> getAllByPrice(Integer way) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        if (way==0)
             wrapper.orderByDesc("price");
        else
            wrapper.orderByAsc("price");
        List<Goods> goods = goodsMapper.selectList(wrapper);
        return goods;

    }

    @Override
    public void resell(Integer goodsId, Double price,Integer userId) {
        goodsMapper.resetGoodsById(goodsId,price);
        //orderService.deleteFromOwn(goodsId,userId);

    }

    @Override
    public Integer getFloor(String type) {
        return goodsMapper.getFloor(type);
    }

    @Override
    public Integer getTotal(String type) {
        return goodsMapper.getTotal(type);
    }

    @Override
    public void add(Goods good) {
        goodsMapper.insert(good);
    }

    @Override
    public List<Goods> getAllByOwner(Integer id) {
        return goodsMapper.getAllByOwner(id);
    }

}
