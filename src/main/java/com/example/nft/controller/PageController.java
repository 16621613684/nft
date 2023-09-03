package com.example.nft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/back")
    public String back(){
        return "redirect:http://localhost:8888/#/dashboard";
    }
}
