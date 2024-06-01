package com.yolo.pojo.dto;

import lombok.Data;

@Data
public class AddressBookDTO {
    private String consignee;
    // 楼栋号
    private String building;
    // 具体位置，门牌号 or 宿舍号
    private String specificLocation;
    // 电话号码
    private String phone;
}
