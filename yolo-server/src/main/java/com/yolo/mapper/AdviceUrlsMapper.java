package com.yolo.mapper;

import com.yolo.pojo.entity.Advice;
import com.yolo.pojo.entity.AdviceUrls;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdviceUrlsMapper {

    void save(AdviceUrls adviceUrls);
}
