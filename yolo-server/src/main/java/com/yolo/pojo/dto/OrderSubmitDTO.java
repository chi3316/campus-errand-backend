package com.yolo.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderSubmitDTO implements Serializable {
    // 订单的标题
    private String title;

    // 用户的地址id，终点
    private Long addressBookId;

    // 快递站, 起点 => 不要传id , 直接给地址
    private String departureAddress;

    // 总金额
    private BigDecimal amount;

    // 订单的备注信息
    private String remark;

    // 照片
    private String imageUrl;
}
