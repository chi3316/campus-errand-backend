package com.yolo.pojo.vo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressVO {
    private Long id;

    private String consignee;

    // 楼栋号
    private String building;

    // 具体位置，门牌号 or 宿舍号
    private String specificLocation;

    // 默认地址 : 1 是 0否
    // private Integer isDefault; 感觉暂时不需要这个

    // 电话号码
    private String phone;
}
