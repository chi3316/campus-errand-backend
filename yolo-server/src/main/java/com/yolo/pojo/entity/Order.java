package com.yolo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String number;
    private String title;
    // 0: 待帮组  1: 已完成
    private Integer status;

    // 下单用户id
    private Long userId;

    // 用户的地址id
    private Long addressBookId;

    private Long receiverId;

    // 快递站id
    private Long expressStationId;

    // 接单人的id
    private Long ReceiverId;

    // 下单时间
    private LocalDateTime orderTime;

    // 支付时间
    private LocalDateTime payTime;

    // 接单时间
    private LocalDateTime takeTime;

    // 完成时间
    private LocalDateTime finishTime;

    // 订单金额
    private BigDecimal amount;

    // 订单的详细信息
    private String info;

    // 备注
    private String remark;

    // 起点
    private String departureAddress;

    // 终点
    private String destinationAddress;
}
