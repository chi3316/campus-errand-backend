package com.yolo.service;

import com.yolo.pojo.dto.OrderSubmitDTO;
import com.yolo.pojo.vo.OrderDetailsVO;
import com.yolo.pojo.vo.OrderSubmitVO;
import com.yolo.pojo.vo.OrderVO;
import com.yolo.result.PageResult;

import java.util.List;

public interface OrderService {
    PageResult<OrderVO> pageQuery(int page, int pageSize, Integer status);

    OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO);

    OrderDetailsVO selectDetails(Long id);

    List<OrderVO> list();
}
