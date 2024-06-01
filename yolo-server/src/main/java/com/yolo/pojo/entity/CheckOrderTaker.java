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
public class CheckOrderTaker {
    private Long id;
    private Long userId;
    private String studentId;
    private String url;
    private Integer success;
    private String name;
    private LocalDateTime createTime;
}
