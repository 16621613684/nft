package com.example.nft.controller;

import com.example.nft.mapper.AuctionMapper;
import com.example.nft.pojo.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class AuctionController {
@Autowired
    AuctionMapper auctionMapper;



    @PostMapping("/addAuction")
    private String addAuction(Auction auction, Model model){
        System.out.println(""+auction.getEndTime());
        if (LocalDateTime.now().isBefore(auction.getEndTime())){
            auctionMapper.insert(auction);
            Auction auction1 = auctionMapper.selectById(3);

            model.addAttribute("a",auction1);
        return "/test";}
        else
            return "";


    }
}
