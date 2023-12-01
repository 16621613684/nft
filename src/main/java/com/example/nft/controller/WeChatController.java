package com.example.nft.controller;


import cn.hutool.extra.qrcode.QrCodeUtil;
import com.example.nft.utils.WxUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * 微信登录
 */
@Controller
public class WeChatController {

    /**
     * pc点击微信登录，生成登录二维码
     * @throws Exception
     */
    @GetMapping(value = "/wxLogin")

    public void wxLoginPage(HttpServletRequest request, HttpServletResponse response,String token) throws Exception {
        System.out.println("js");
        //redirect_uri是回调的地址 注意要转成UrlEncode格式
        String urlEncode=URLEncoder.encode("http://w78k8a.natappfree.cc/pcLogin","UTF-8");
        String url1 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx70444c43c83c31b2&redirect_uri="+urlEncode+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        //生成二维码的，扫描后跳转上面的地址
        QrCodeUtil.generate(url1, 300, 300, "jpg", response.getOutputStream());

    }

    /**
     * 扫描二维码授权成功，取到code，就是回调方法（redirect_uri访问地址）
     *
     * @author js
     * @param code
     * @param state
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pcLogin")

    public String pcCallback(String code, String state, HttpServletRequest request, HttpServletResponse response,
                             HttpSession session) throws Exception {
        String loginAcessToken = WxUtils.getLogin("wx70444c43c83c31b2", "f3d6283e791725dc5fb8a47a83cf2005", code);
        return "redirect:/home";
    }

}
