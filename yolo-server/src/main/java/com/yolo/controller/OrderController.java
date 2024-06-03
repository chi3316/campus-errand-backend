package com.yolo.controller;

import com.yolo.pojo.dto.OrderSubmitDTO;
import com.yolo.pojo.vo.OrderDetailsVO;
import com.yolo.pojo.vo.OrderSubmitVO;
import com.yolo.pojo.vo.OrderVO;
import com.yolo.result.PageResult;
import com.yolo.result.Result;
import com.yolo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "订单相关接口")
@RequestMapping("user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询全部订单
     * @return
     */
    @ApiOperation("查询所有订单")
    @GetMapping("/listAll")
    public Result<List<OrderVO>> findAll() {
        log.info("查询所有订单");
        List<OrderVO> orderVOList = orderService.list();
        return Result.success(orderVOList);
    }

    /**
     * 订单分页查询
     * @return
     */
    @GetMapping("/listByStatus")
    @ApiOperation("用户订单分页查询")
    public Result<PageResult<OrderVO>> historyOrders(int page, int pageSize, Integer status) {
        log.info("订单分页查询：pageNum:{}, pageSize:{}, status:{}",page,pageSize,status);
        PageResult<OrderVO> pageResult = orderService.pageQuery(page,pageSize,status);
        return Result.success(pageResult);
    }

    /**
     * 提交订单
     * @param orderSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> orderSubmit(@RequestBody OrderSubmitDTO orderSubmitDTO) {
        log.info("用户提交订单：{}",orderSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submit(orderSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    @ApiOperation("查询订单详情")
    @GetMapping("/orderDetail/{id}")
    public Result<OrderDetailsVO> orderDetails(@PathVariable Long id) {
        log.info("查询订单详情，订单id : {}" , id);
        OrderDetailsVO orderDetailsVO = orderService.selectDetails(id);
        return Result.success(orderDetailsVO);
    }
}
