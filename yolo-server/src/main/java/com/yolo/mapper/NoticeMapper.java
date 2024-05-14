package com.yolo.mapper;

import com.mysql.cj.protocol.x.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoticeMapper {
    @Select("select * from notices ")
    Notice selectAll();
}
