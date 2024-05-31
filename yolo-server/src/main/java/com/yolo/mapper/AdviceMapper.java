package com.yolo.mapper;

import com.yolo.pojo.entity.Advice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdviceMapper {

    void save(Advice advice);
}
