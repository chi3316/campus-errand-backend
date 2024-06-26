package com.yolo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yolo.constant.MessageConstant;
import com.yolo.constant.OrderStatusConstant;
import com.yolo.context.BaseContext;
import com.yolo.exception.AddressBookBusinessException;
import com.yolo.mapper.AddressBookMapper;
import com.yolo.mapper.OrderMapper;
import com.yolo.pojo.dto.OrderSubmitDTO;
import com.yolo.pojo.dto.OrdersPageQueryDTO;
import com.yolo.pojo.entity.AddressBook;
import com.yolo.pojo.entity.Order;
import com.yolo.pojo.entity.User;
import com.yolo.pojo.vo.OrderDetailsVO;
import com.yolo.pojo.vo.OrderSubmitVO;
import com.yolo.pojo.vo.OrderVO;
import com.yolo.result.PageResult;
import com.yolo.service.OrderService;
import com.yolo.service.UserService;
import com.yolo.context.BaseContext;
import lombok.extern.java.Log;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private UserService userService;

    @Override
    public PageResult<OrderVO> pageQuery(int page, int pageSize, Integer status) {
        // 获取用户id , 封装到DTO对象中
        OrdersPageQueryDTO ordersPageQueryDTO = new OrdersPageQueryDTO();
        ordersPageQueryDTO.setUserId(BaseContext.getCurrentId());
        ordersPageQueryDTO.setStatus(status);
        // 进行分页查询
        PageHelper.startPage(page, pageSize);
        Page<Order> pageResults = orderMapper.pageQuery(ordersPageQueryDTO);
        // 将查询到的数据封装到VO对象中并且返回
        List<OrderVO> orderVOs = new ArrayList<>();
        for (Order order : pageResults) {
            Long userId = order.getUserId();
            User user = userService.getById(userId);
            OrderVO orderVO = new OrderVO();
            orderVO.setAvatar(user.getAvatar());
            BeanUtils.copyProperties(order, orderVO);
            orderVOs.add(orderVO);
        }
        return new PageResult<>(pageResults.getTotal(), orderVOs);
    }

    @Override
    public OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO) {
        // 获取DTO数据，并添加对应数据 ： 地址信息，时间信息，订单号，封装到Order中
        // 获取id查询地址簿获取地址
        // 判断地址簿是否为空，空 => 抛出异常，前端提醒用户添加收获地址
        AddressBook addressBook = addressBookMapper.getById(orderSubmitDTO.getAddressBookId());
        if (addressBook == null) {
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }
        // 根据传进来的起点id , 终点id 来查询出地址的具体信息，并填充到order对象中
        String destinationAddress = addressBook.getBuilding() + " " + addressBook.getSpecificLocation();

        Order order = new Order();
        BeanUtils.copyProperties(orderSubmitDTO, order);
        order.setOrderTime(LocalDateTime.now());
        order.setNumber(String.valueOf(System.currentTimeMillis()));
        // 设置订单状态,用户id
        order.setStatus(OrderStatusConstant.WAIT_FOR_HELP);
        order.setUserId(BaseContext.getCurrentId());
        order.setDestinationAddress(destinationAddress);

        // 插入到订单表中
        orderMapper.insert(order);
        // 将生成的订单id封装与下单时间封装到VO对象中，返回
        OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
                .id(order.getId())
                .orderTime(order.getOrderTime())
                .title(order.getTitle())
                .orderNumber(order.getNumber())
                .orderAmount(order.getAmount())
                .build();
        return orderSubmitVO;
    }

    @Override
    public OrderDetailsVO selectDetails(Long id) {
        // 从订单表里面查id对应的订单的相关字段
        Order order = orderMapper.select(id);
        // 封装成OrderDetailsVO对象返回
        OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
        BeanUtils.copyProperties(order, orderDetailsVO);
        // 获取下单用户头像
        User user = userService.getById(order.getUserId());
        orderDetailsVO.setAvatar(user.getAvatar());
        // 设置发布订单的用户ID
        orderDetailsVO.setUserId(order.getUserId());
        // 设置详细信息
        orderDetailsVO.setInfo(order.getInfo());
        // 设置图片
        orderDetailsVO.setImageUrl(order.getImageUrl());
        return orderDetailsVO;
    }

    @Override
    public List<OrderVO> list() {
        List<Order> orders = orderMapper.selectAll();
        // 将查询到的数据封装到VO对象中并且返回
        List<OrderVO> orderVOs = new ArrayList<>();
        for (Order order : orders) {
            Long userId = order.getUserId();
            User user = userService.getById(userId);
            OrderVO orderVO = new OrderVO();
            orderVO.setAvatar(user.getAvatar());
            BeanUtils.copyProperties(order, orderVO);
            orderVOs.add(orderVO);
        }
        return orderVOs;
    }

    @Override
    public void acceptOrder(Long id) {
        // 从订单表里面查id对应的订单的相关字段
        Order order = orderMapper.select(id);
        // 设置订单状态为已帮助
        order.setStatus(1);
        // 设置接单时间
        order.setTakeTime(LocalDateTime.now());
        // 设置接单人用户id
        order.setReceiverId(BaseContext.getCurrentId());
        orderMapper.update(order);
    }

    @Override
    public void ackOrder(Long id) {
        // 从订单表里面查id对应的订单的相关字段
        Order order = orderMapper.select(id);
        // 设置订单状态为已完成
        order.setStatus(2);
        // 设置完成时间
        order.setFinishTime(LocalDateTime.now());
        orderMapper.update(order);
    }

}
