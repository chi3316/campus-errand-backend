package com.yolo.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateDTO implements Serializable {
    private String avatar;
    private String name;
    private String phone;
}