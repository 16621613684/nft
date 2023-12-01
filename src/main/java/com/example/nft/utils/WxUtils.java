package com.example.nft.utils;


import com.alibaba.fastjson.JSON;
import com.example.nft.pojo.CodeToken;
import com.example.nft.pojo.WeChatPerson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 扫码登录帮助类
 */
public class WxUtils {
    /**
     * 获取扫码人的个人信息
     * @param appid 账号appid
     * @param secret 账号secret
     * @param code 状态
     * @return 返回值为首页地址，用于授权成功后自己跳转自己的首页
     * @throws Exception
     */
    public static String getLogin(String  appid, String secret, String code) throws Exception{
        HttpClient httpclient =  HttpClients.createDefault();
        //用code交换token，code为扫码后微信服务器响应来的值
        String smsUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ appid +"&SECRET="+ secret + "&code=" + code + "&grant_type=authorization_code";
        //发请求
        HttpGet httpGet = new HttpGet(smsUrl);
        String strResult = "";
        //接收返回的数据，转成utf-8格式
        HttpResponse response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
        }
        //处理数据，存到实体类CodeToken里面
        CodeToken codeToken = JSON.parseObject(strResult, CodeToken.class);
        System.out.println(codeToken);

        //用token交换扫码人的个人信息
        String smsUrl1="https://api.weixin.qq.com/sns/userinfo?access_token="+codeToken.getAccess_token()+"&openid="+codeToken.getOpenid()+"&lang=zh_CN";
        //向微信端发请求
        HttpGet httpGet1 = new HttpGet(smsUrl1);
        String strResult1 = "";
        //接收数据
        HttpResponse response1 = httpclient.execute(httpGet1);
        if (response1.getStatusLine().getStatusCode() == 200) {
            strResult1 = EntityUtils.toString(response1
                    .getEntity(),"UTF-8");
        }
        //处理数据，存到实体类中  后把用户个人信息存到数据库，由于我是测试在这里我就不存了
        WeChatPerson weChatPerson = JSON.parseObject(strResult1, WeChatPerson.class);

        System.out.println(weChatPerson);
        //return "本处为首页url，即扫码成功后前台重定向到页面"+"用户名："+weChatPerson.getNickname()+"唯一id"+weChatPerson.getOpenid();
        return "/";
    }

}
