package com.yolo.mapper;

import com.yolo.pojo.entity.Notices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {
    @Select("select * from notices ORDER BY id ASC")
    List<Notices> selectAll();
}
