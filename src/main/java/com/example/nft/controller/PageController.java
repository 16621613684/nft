package com.example.nft.controller;

import com.example.nft.service.WxSignatureCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @Autowired
    private WxSignatureCheckService wxSignatureCheckService;
    @GetMapping("/back")
    public String back(){
        return "redirect:http://localhost:8888/#/dashboard";
    }
    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @ResponseBody
    @RequestMapping("/wxSignatureCheck")
    public String wxSignatureCheck(
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce,
            @RequestParam(value = "echostr") String echostr
    ){
        return wxSignatureCheckService.wxSignatureCheck(signature, timestamp, nonce, echostr);
    }

    @GetMapping("/certify")
    public String certify(){
        return "certify";
    }

    @GetMapping("/test")
    public String test(){
        return "info";
    }

}
