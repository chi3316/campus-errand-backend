package com.yolo.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    //订单id
    private Long id;

    private String number;
    private String title;
    //0: 待帮组  1: 已完成
    private Integer status;

    //下单用户id
    private Long userId;

    //地址id
    private Long addressBookId;

    //下单时间
    private LocalDateTime orderTime;

    //订单金额
    private Double amount;

    //备注
    private String remark;
}
