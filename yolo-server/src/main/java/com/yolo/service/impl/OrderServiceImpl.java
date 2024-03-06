package com.yolo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yolo.constant.MessageConstant;
import com.yolo.context.BaseContext;
import com.yolo.exception.AddressBookBusinessException;
import com.yolo.mapper.AddressBookMapper;
import com.yolo.mapper.OrderMapper;
import com.yolo.pojo.dto.OrderSubmitDTO;
import com.yolo.pojo.dto.OrdersPageQueryDTO;
import com.yolo.pojo.entity.AddressBook;
import com.yolo.pojo.entity.Order;
import com.yolo.pojo.vo.OrderSubmitVO;
import com.yolo.pojo.vo.OrderVO;
import com.yolo.result.PageResult;
import com.yolo.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressBookMapper addressBookMapper;
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

    @Override
    public OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO) {
        //获取DTO数据，并添加对应数据 ： 地址信息，时间信息，订单号，封装到Order中
        //获取id查询地址簿获取地址
        //判断地址簿是否为空，空 => 抛出异常，前端提醒用户添加收获地址
        AddressBook addressBook = addressBookMapper.getById(orderSubmitDTO.getAddressBookId());
        if(addressBook == null) {
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }
        Order order = new Order();
        BeanUtils.copyProperties(orderSubmitDTO,order);
        order.setOrderTime(LocalDateTime.now());
        order.setNumber(String.valueOf(System.currentTimeMillis()));
        //插入到订单表中
        orderMapper.insert(order);
        //将生成的订单id封装与下单时间封装到VO对象中，返回
        OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
                .id(order.getId())
                .orderTime(LocalDateTime.now())
                .orderNumber(order.getNumber())
                .build();
        return orderSubmitVO;
    }
}
