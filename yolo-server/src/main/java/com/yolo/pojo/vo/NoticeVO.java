package com.yolo.pojo.vo;

import com.yolo.pojo.entity.Notices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeVO {
    private List<Notices> notices;
}
