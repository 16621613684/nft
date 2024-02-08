package com.example.nft.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.nft.mapper.BalanceMapper;
import com.example.nft.mapper.UserMapper;
import com.example.nft.pojo.Auction;
import com.example.nft.pojo.BalanceHistory;
import com.example.nft.pojo.Goods;
import com.example.nft.service.AuctionService;
import com.example.nft.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask {
    @Autowired
    AuctionService auctionService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BalanceMapper balanceMapper;


    @Scheduled(cron = "0/10 * * * * ?")
    //时间间隔：10秒
    @Scheduled(fixedRate=5000)
    private void configureTasks() {
        LambdaQueryWrapper<Auction> wrapper = new LambdaQueryWrapper<Auction>().eq(Auction::getStatus, 0);
        //拿到所有处于拍卖中且到期的
        List<Auction> list = auctionService.list(wrapper).stream().
                filter(a->LocalDateTime.now().isAfter(a.getEndTime())).
                collect(Collectors.toList());
        //遍历处理
        for (Auction auction : list) {
            System.out.println("一拍卖已到期："+auction.getId());
            //改变状态
            auction.setStatus(1);
            auctionService.updateById(auction);
            if (auction.getHighestBidderId()!=null){
            //改变商品状态
            Goods byId = goodsService.getById(auction.getGoodId());
            byId.setGoodsStatus(1);
            byId.setPrice(auction.getCurrentPrice());
            //余额变更

            userMapper.addBalanceById(byId.getPrice(), byId.getOwner());
            userMapper.minusBalanceById(byId.getPrice() , auction.getHighestBidderId());

            //交易记录
            Date now = new Date();
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            balanceMapper.insert(new BalanceHistory(0, auction.getHighestBidderId(),byId.getOwner(), byId.getId(), 0,byId.getPrice(),sdf.format(now)));
            //拥有者变更
            byId.setOwner(auction.getHighestBidderId());
            goodsService.updateById(byId);}


        }
        System.err.println("轮询中..." );
    }
}
