package com.example.nft.controller;

import com.example.nft.mapper.GoodsMapper;
import com.example.nft.pojo.Cart;
import com.example.nft.pojo.CartItem;
import com.example.nft.pojo.Goods;
import com.example.nft.pojo.User;
import com.example.nft.service.CartService;
import com.example.nft.service.GoodsService;
import com.example.nft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    //加入购物车
    @GetMapping(value = {"/sort/{id}/{loc}","/order/cart/{id}/{type}","/mohu/{id}/{content}"})
    public String addCart(@PathVariable("id") Integer goodsId, @PathVariable(value = "content",required = false) String content, @PathVariable(value = "loc",required = false)String loc, @PathVariable(required = false) String type, HttpSession session) throws UnsupportedEncodingException, UnsupportedEncodingException {
        User currUser = (User)session.getAttribute("currUser");
        if (cartService.getById(goodsId,currUser.getId())==null){
        CartItem cartItem = new CartItem(goodsId, currUser.getId());
        cartService.addCart(cartItem);}

        if (loc!=null){
            return "redirect:/"+loc;}
        if (type!=null){
            return "redirect:/order/"+URLEncoder.encode(type,"UTF-8");
        }
        if (content!=null)
            return "redirect:/search?mohu="+ URLEncoder.encode(content,"UTF-8");

        return "redirect:/search";
}
    //查看购物车
    @GetMapping("/myCart")
    public String myCart( HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        Cart cart = cartService.myCart(currUser.getId());
        currUser.setCart(cart);
        session.setAttribute("currUser",currUser);
        return "cart";
    }
    //删除购物车项
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id,HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        cartService.deleteById(id,currUser.getId());
        return "redirect:/myCart";
    }
    //清空购物车
    @GetMapping("/clear")
    public String clear(HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        cartService.deleteAll(currUser.getId());
        return "redirect:/myCart";
    }//继续购物
    @GetMapping("/goShop")
    public String goShop(){
        return "redirect:/search";
    }

}
