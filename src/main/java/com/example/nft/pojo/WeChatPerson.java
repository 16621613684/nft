package com.example.nft.pojo;

import lombok.Data;

@Data
public class WeChatPerson {
    /**
     * 用户的唯一标识
     */
    private String openid;
    /**
     *用户昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer sex;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 普通用户个人资料填写的城市
     */
    private String city;
    /**
     * 用户个人资料填写的省份
     */
    private String province;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;
}
