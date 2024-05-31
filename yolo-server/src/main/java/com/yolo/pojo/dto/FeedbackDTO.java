package com.yolo.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FeedbackDTO implements Serializable {
    private List<String> urls; // 反馈的图片的url
    private String text; // 反馈的文本
}
