package com.example.nft.controller;

import com.example.nft.pojo.Goods;
import com.example.nft.pojo.OrderBean;
import com.example.nft.pojo.User;
import com.example.nft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    //结账
    @RequestMapping("/checkout")
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean() ;
        Date now = new Date();
        int year = now.getYear();
        int month = now.getMonth() ;
        int day = now.getDate();
        int hour = now.getHours();
        int min = now.getMinutes() ;
        int sec = now.getSeconds() ;
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+year+month+day+hour+min+sec);
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        orderBean.setOrderDate(sdf.format(now));

        User user =(User)session.getAttribute("currUser");
        orderBean.setOrderUser(user.getId());
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setGoodsCount(user.getCart().getTotalCount());
        orderBean.setOrderStatus(0);

        orderService.checkout(orderBean,user.getId());
        List<OrderBean> orderBeans = orderService.showOrder(user.getId());
        session.setAttribute("Orders",orderBeans);
        return "order";
    }
    @GetMapping("/toOrder")
    public String toOrder(HttpSession session){
        User user =(User)session.getAttribute("currUser");
        List<OrderBean> orderBeans = orderService.showOrder(user.getId());
        session.setAttribute("Orders",orderBeans);
        return "order";
    }
    @GetMapping("/detail/{id}")
    public String orderDetail(@PathVariable Integer id, Model model){
        List<Goods> goods = orderService.getGoods(id);
        OrderBean orderById = orderService.getOrderById(id);
        model.addAttribute("goods",goods);
        model.addAttribute("orderDetail",orderById);
        return "orderDetail";
    }
}
