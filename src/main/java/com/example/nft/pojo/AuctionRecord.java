package com.example.nft.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName auction_record
 */
@TableName(value ="auction_record")
@Data
@AllArgsConstructor
public class AuctionRecord implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String userName;

    private Integer auctionId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") //接收时间类型
    private LocalDateTime biddingTime;

    private Double price;

    private static final long serialVersionUID = 1L;
}
