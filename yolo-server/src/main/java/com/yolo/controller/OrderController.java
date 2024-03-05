package com.yolo.controller;

import com.yolo.result.PageResult;
import com.yolo.result.Result;
import com.yolo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "订单相关接口")
@RequestMapping("user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 订单分页查询
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation("用户订单分页查询")
    public Result<PageResult> historyOrders(int page, int pageSize, Integer status) {
        PageResult pageResult = orderService.pageQuery(page,pageSize,status);
        return Result.success(pageResult);
    }
}
