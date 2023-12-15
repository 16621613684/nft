package com.example.nft.controller;

import com.example.nft.service.GoodsService;
import com.example.nft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyzeController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;

    @GetMapping("/analyze")
    public String analyze(Model model)
    {
        model.addAttribute("goodsCount",goodsService.count());
        model.addAttribute("avGoodsCount",goodsService.getAllGoods().stream().filter(g -> g.getGoodsStatus() == 0).count());
        model.addAttribute("userCount",userService.count());
        return "analyze";

    }
}
