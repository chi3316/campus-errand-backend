package com.yolo.mapper;

import com.yolo.pojo.entity.Advice;
import com.yolo.pojo.entity.CheckOrderTaker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckOrderTakerMapper {

    void insert(CheckOrderTaker checkOrderTaker);
}
