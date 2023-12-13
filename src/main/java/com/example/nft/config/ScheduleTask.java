package com.example.nft.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.nft.pojo.Auction;
import com.example.nft.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask {
    @Autowired
    AuctionService auctionService;

    //3.添加定时任务
    @Scheduled(cron = "0/10 * * * * ?")
    //时间间隔：10秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        LambdaQueryWrapper<Auction> wrapper = new LambdaQueryWrapper<Auction>().eq(Auction::getStatus, 0);
        List<Auction> list = auctionService.list(wrapper).stream().
                filter(a->LocalDateTime.now().isAfter(a.getEndTime())).
                collect(Collectors.toList());
        for (Auction auction : list) {
            auction.setStatus(1);
            auctionService.updateById(auction);
        }
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
