package com.yolo.pojo.entity;

public class AddressBook {
    private Long id;

    private Long userId;

    private String consignee;

    //楼栋号
    private String building;

    //具体位置，门牌号 or 宿舍号
    private String specificLocation;

    //默认地址 : 1 是 0否
    private Integer is_default;
}
