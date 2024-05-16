package com.yolo.mapper;

import com.github.pagehelper.Page;
import com.yolo.pojo.dto.OrdersPageQueryDTO;
import com.yolo.pojo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    Page<Order> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    void insert(Order order);

    @Select("select * from campus_errand.orders where id = #{id}")
    Order select(Long id);
}
