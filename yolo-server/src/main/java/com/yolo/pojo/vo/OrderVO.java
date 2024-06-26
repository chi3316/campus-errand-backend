package com.yolo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO implements Serializable {
    private Long id;

    private String title;

    //0: 待帮助  1: 已帮助  2: 已完成
    private Integer status;

    // 用户id
    private Long userId;

    //下单时间
    private LocalDateTime orderTime;

    // 订单金额
    private BigDecimal amount;

    // 起点
    private String departureAddress;

    // 终点
    private String destinationAddress;

    // 发布订单的用户的头像
    private String avatar;

    // 订单备注信息
    private String remark;

    // 返回接单人id
    private Long receiverId;
}
