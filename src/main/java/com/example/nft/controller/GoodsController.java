package com.example.nft.controller;

import com.example.nft.pojo.Goods;
import com.example.nft.pojo.User;
import com.example.nft.service.GoodsService;
import com.example.nft.service.OrderService;
import com.example.nft.utils.Pinyin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    //首页
    @RequestMapping("/home")
    public String index(HttpSession session,Model model){
        return init(session, model);
    }
    //降序
    @GetMapping("/orderDesc")
    public String searchDesc(Model model){
        List<Goods> allByPrice = goodsService.getAllGoods().stream()
                .sorted((o1, o2) -> o2.getPrice().intValue()-o1.getPrice().intValue())
                .collect(Collectors.toList());
        model.addAttribute("resultGoods",handleSrc(allByPrice));
        return "goods";
    }//升序
    @GetMapping("/orderAsc")
    public String searchAsc(Model model){
        List<Goods> allByPrice = goodsService.getAllGoods().stream()
                .sorted((o1, o2) -> o1.getPrice().intValue()-o2.getPrice().intValue())
                .collect(Collectors.toList());
        model.addAttribute("resultGoods",handleSrc(allByPrice));
        return "goods";
    }
   //搜索
    @GetMapping(value = "/search")
    public String search(Model model,@RequestParam(value = "mohu",defaultValue = "") String mohu){
        Set<Goods> goods = goodsService.searchGoods(mohu);
        model.addAttribute("resultGoods",handleSrc(goods));
        return "goods";
    }
    //按类别筛选
    @GetMapping("/order/{type}")
    public String searchType(@PathVariable String type, Model model){

        Set<Goods> goods = goodsService.searchGoodsByType(type);
        model.addAttribute("resultGoods",handleSrc(goods));
        return "goods";
    }
    //访客登陆
    @GetMapping("/visit")
    public String visit(HttpSession session,Model model){
        return init(session, model);
    }

    public String init(HttpSession session, Model model) {
        List<Goods> allGoods = goodsService.getAllGoods();
        session.setAttribute("goodsList",allGoods);

        model.addAttribute("zodiac",goodsService.getFloor("十二生肖"));
        model.addAttribute("painting",goodsService.getFloor("国画"));
        model.addAttribute("head",goodsService.getFloor("像素头像"));
        model.addAttribute("opera",goodsService.getFloor("脸谱"));
        model.addAttribute("cartoon",goodsService.getFloor("漫画"));

        model.addAttribute("zodiacT",goodsService.getTotal("十二生肖"));
        model.addAttribute("paintingT",goodsService.getTotal("国画"));
        model.addAttribute("headT",goodsService.getTotal("像素头像"));
        model.addAttribute("operaT",goodsService.getTotal("脸谱"));
        model.addAttribute("cartoonT",goodsService.getTotal("漫画"));

        return "home";
    }

    //转卖
    @PostMapping("/resell")
    public String resell( Integer id,String price,HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        Double price2 = Double.parseDouble(price);
        goodsService.resell(id, price2,currUser.getId());
        return "redirect:/toOwn";
    }
    //我的藏品
    @GetMapping("/toOwn")
    public String toOwn(HttpSession session,Model model){
        User currUser = (User)session.getAttribute("currUser");
        List<Goods> ownGoods = goodsService.getAllByOwner(currUser.getId());
        if (ownGoods.size()==0)model.addAttribute("ownGoods",new ArrayList<>());
        model.addAttribute("ownGoods",handleSrc(ownGoods));
        return "own";
    }
    //去创作
    @GetMapping("/toCreate")
    public String toCreate(){
        return "create";
    }

    @PostMapping("/addGood")
    public String addGood(String goodsName, String author, Double price, @RequestPart("file") MultipartFile file,String type) throws IOException {
        String property = System.getProperty("user.dir");
        String path = property + "/target/classes/static/images/";
        String filename= Pinyin.toPinyin(goodsName);
        String filepath=path+filename+".png";
        //保存文件
        file.transferTo(new File(filepath));
        //存入数据库
        String dbpath=filename+".png";
        Goods good = new Goods(dbpath, goodsName, price, author, type, 0);
        goodsService.add(good);
        return "home";
    }

    public Collection<Goods> handleSrc(Collection<Goods> source){
        for (Goods good : source) {
            if (good.getGoodsImg().contains(".mp3")){
                good.setAudio(good.getGoodsImg());
                good.setGoodsImg(null);
            }
        }return source;
    }

}
