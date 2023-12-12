package com.example.nft.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName auction
 */
@TableName(value ="auction")
@Data
@AllArgsConstructor
public class Auction implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer goodId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") //接收时间类型
    private LocalDateTime endTime;

    private Double startingPrice;

    private Double currentPrice;

    private Integer highestBidderId;

    private static final long serialVersionUID = 1L;
}
