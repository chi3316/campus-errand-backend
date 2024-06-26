package com.yolo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverInfoVO implements Serializable {

    private Long id;
    private String name; // 昵称
    private String avatar; // 头像url
    private String phone; // 电话号码
}
