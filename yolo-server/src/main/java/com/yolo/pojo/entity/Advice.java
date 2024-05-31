package com.yolo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Advice implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String text; // 反馈的文本
}
