package com.yolo.service;

import com.yolo.result.PageResult;

public interface OrderService {
    PageResult pageQuery(int page, int pageSize, Integer status);
}
