package com.yolo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yolo.context.BaseContext;
import com.yolo.mapper.OrderMapper;
import com.yolo.pojo.dto.OrdersPageQueryDTO;
import com.yolo.pojo.entity.Order;
import com.yolo.pojo.vo.OrderVO;
import com.yolo.result.PageResult;
import com.yolo.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public PageResult pageQuery(int page, int pageSize, Integer status) {
        //获取用户id , 封装到DTO对象中
        OrdersPageQueryDTO ordersPageQueryDTO = new OrdersPageQueryDTO();
        ordersPageQueryDTO.setUserId(BaseContext.getCurrentId());
        ordersPageQueryDTO.setStatus(status);
        //进行分页查询
        PageHelper.startPage(page,pageSize);
        Page<Order> pageResults = orderMapper.pageQuery(ordersPageQueryDTO);
        //将查询到的数据封装到VO对象中并且返回
        List<OrderVO> orderVOS = new ArrayList<>();
        for(Order order : pageResults) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order,orderVO);
            orderVOS.add(orderVO);
        }
        PageResult result = new PageResult(pageResults.getTotal(), pageResults.getResult());
        return result;
    }
}
