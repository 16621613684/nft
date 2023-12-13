package com.example.nft.controller;

import com.example.nft.mapper.BalanceMapper;
import com.example.nft.mapper.GoodsMapper;
import com.example.nft.mapper.OrderMapper;
import com.example.nft.mapper.UserMapper;
import com.example.nft.pojo.BalanceHistory;
import com.example.nft.pojo.BalanceVO;
import com.example.nft.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BalanceController {
    @Autowired
    BalanceMapper balanceMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/toWallet")
    public String balanceDetail(HttpSession session, Model model){
        User currUser = (User)session.getAttribute("currUser");
        List<BalanceHistory> balanceHistories = balanceMapper.selectAllByUserId(currUser.getId());
        if (balanceHistories.isEmpty())
            return "wallet";
        ArrayList<BalanceVO> balanceVO = new ArrayList<>();
        //遍历数据库中 交易记录，转化为BalanceVO
        for (BalanceHistory bh : balanceHistories) {
            String buyerName=userMapper.getUsernameById(bh.getBuyerId());
            String sellerName=userMapper.getUsernameById(bh.getSellerId());
            String goodsName= goodsMapper.getGoodsNameById(bh.getGoodsId());
            String goodsImg = goodsMapper.getGoodsImgById(bh.getGoodsId());
            String orderNo=orderMapper.getOrderNoById(bh.getOrderId());
            balanceVO.add(new BalanceVO(bh.getBuyerId(),bh.getSellerId(),buyerName,sellerName,bh.getGoodsId(),goodsName,goodsImg,bh.getOrderId(),orderNo,bh.getChange(),bh.getTime()));
        }
        List<BalanceVO> buyHistory = balanceVO.stream().filter(b -> b.getBuyerId() == currUser.getId()).collect(Collectors.toList());
        List<BalanceVO> sellHistory = balanceVO.stream().filter(b -> b.getSellerId() == currUser.getId()).collect(Collectors.toList());

        model.addAttribute("buyHistory",buyHistory);
        model.addAttribute("sellHistory",sellHistory);
        //获取最新的余额值
        model.addAttribute("balance",userMapper.selectUserById(currUser.getId()).getBalance());
        return "wallet";
    }
}
