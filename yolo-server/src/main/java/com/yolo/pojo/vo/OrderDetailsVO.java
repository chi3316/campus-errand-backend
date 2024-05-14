package com.yolo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsVO {
    private String number;

    private String address;

    // 用户的地址id，终点
    private String addressBookId;

    private double amount;

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


}
