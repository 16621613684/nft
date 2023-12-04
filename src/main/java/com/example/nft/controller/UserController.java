package com.example.nft.controller;

import com.example.nft.mapper.UserMapper;
import com.example.nft.pojo.User;
import com.example.nft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public String login(User user, HttpSession session){

        User curUser = userService.login(user);
        if (curUser!=null)
        {
          /*  Cart cart = cartItemService.getCart(user);
            user.setCart(cart);*/
            session.setAttribute("currUser",curUser);
            return "redirect:/home";
        }

        return "login";
    }
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    @GetMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("currUser");
        return "home";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.register(user);
        return "login";
    }

    @GetMapping("/")
    public String index(){
        return "login";
    }
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    @GetMapping("/backHome")
    public String home(){
        return "redirect:/home";
    }
    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }
}
