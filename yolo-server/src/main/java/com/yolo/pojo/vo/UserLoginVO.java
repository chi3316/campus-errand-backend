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
public class UserLoginVO implements Serializable {

    private Long id;
    private String openid; // 微信用户唯一标识
    private String token; // jwt令牌
    private String name; // 昵称
    private String avatar; // 头像url
    private String phone; // 电话号码
    private Long expiration; // 令牌过期时间
    private Integer isOrderTaker; // 用户是否具有接单资格
}
