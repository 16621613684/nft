package com.example.nft.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.nft.mapper.AuctionMapper;
import com.example.nft.mapper.AuctionRecordMapper;
import com.example.nft.pojo.Auction;
import com.example.nft.pojo.AuctionRecord;
import com.example.nft.pojo.User;
import com.example.nft.service.AuctionService;
import com.example.nft.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AuctionController {
    @Autowired
    AuctionMapper auctionMapper;
    @Autowired
    AuctionService auctionService;
    @Autowired
    AuctionRecordMapper auctionRecordMapper;
    @Autowired
    GoodsService goodsService;

    //发起拍卖
    @PostMapping("/addAuction")
    private String addAuction( Integer goodId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endTime,Double startingPrice) {
        Auction auction = new Auction(goodId, endTime, startingPrice);
        //如果时间合法
        if (LocalDateTime.now().isBefore(auction.getEndTime())) {
            auction.setStatus(0);
            auctionMapper.insert(auction);
            return "redirect:/toOwn";
        } else
            return "redirect:/toOwn";
    }
    //查询所有拍卖
    @GetMapping("/auctionList")
    private String auctionList(Model model){
        LambdaQueryWrapper<Auction> lw = new LambdaQueryWrapper<Auction>().eq(Auction::getStatus, 0);
        LambdaQueryWrapper<Auction> lw2 = new LambdaQueryWrapper<Auction>().eq(Auction::getStatus, 1);
        //拍卖中的
        List<Auction> availableList = auctionService.list(lw);
        availableList.stream().forEach(t->t.setGoodInfo(goodsService.getById(t.getGoodId())));
        //已结束的
        List<Auction> soldList = auctionService.list(lw2);
        soldList.stream().forEach(t->t.setGoodInfo(goodsService.getById(t.getGoodId())));

        model.addAttribute("availableList",availableList);
        model.addAttribute("soldList",soldList);

        return"auction";
    }

    //出价
    @PostMapping("/bid")
    private String bid(Integer auctionId, Double price, HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        //添加出价记录
        AuctionRecord record = new AuctionRecord(0, currUser.getId(), auctionId, LocalDateTime.now(), price);
        auctionRecordMapper.insert(record);
        //修改拍卖信息
        Auction auction = auctionMapper.selectById(auctionId);
        if (price>auction.getCurrentPrice()){
            auction.setCurrentPrice(price);
            auction.setHighestBidderId(currUser.getId());
            auctionMapper.updateById(auction);
        }


        return "redirect:/auctionList";
    }
}
