package com.yolo.service;

import com.yolo.pojo.dto.OrderSubmitDTO;
import com.yolo.pojo.vo.OrderSubmitVO;
import com.yolo.result.PageResult;

public interface OrderService {
    PageResult pageQuery(int page, int pageSize, Integer status);

    OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO);
}
