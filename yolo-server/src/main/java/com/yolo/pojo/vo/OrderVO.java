package com.yolo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO implements Serializable {
    private Long id;

    private String number;

    private String title;

    //0: 待帮组  1: 已完成
    private Integer status;

    //接单人的id
    private Long ReceiverId;

    //下单时间
    private LocalDateTime orderTime;

    //接单时间
    private LocalDateTime takeTime;

    //完成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //不生效，不知道为啥
    private LocalDateTime finishTime;

    //订单金额
    private Double amount;

    //备注
    private String remark;

    //起点
    private String departureAddress;

    //终点
    private String destinationAddress;

}
