package com.yolo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // 微信用户唯一标识
    private String openid;

    // 用户名
    private String name;

    // 手机号
    private String phone;

    // 性别 0 女 1 男
    private String gender;

    // 身份证号
    private String idNumber;

    // 头像
    private String avatar;

    // 注册时间
    private LocalDateTime createTime;

    // 是否是接单员  -1 : 不是;   1 : 待审查;   0 : 修改为不是（默认是null） 2 : 审查通过 3 : 审查不通过
    private Integer isOrderTaker;

}
