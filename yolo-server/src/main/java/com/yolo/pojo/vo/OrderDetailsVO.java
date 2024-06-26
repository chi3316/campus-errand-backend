package com.yolo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsVO {
    private String number;

    // 用户的地址id，终点
    private Long addressBookId;

    private BigDecimal amount;

    private String title;

    private LocalDateTime orderTime;

    private LocalDateTime payTime;

    private LocalDateTime takeTime;

    private LocalDateTime finishTime;

    private String remark;

    // 起点
    private String departureAddress;

    // 终点
    private String destinationAddress;

    // 状态
    private Integer status;

    private String avatar;

    // 接单人用户id
    private Long receiverId;

}
