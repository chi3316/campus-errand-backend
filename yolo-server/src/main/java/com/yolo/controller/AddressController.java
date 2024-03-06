package com.yolo.controller;

import com.yolo.pojo.entity.AddressBook;
import com.yolo.result.Result;
import com.yolo.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "地址簿相关接口")
@RestController
@RequestMapping("/user/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressBookService addressBookService;
    /**
     *向地址簿中添加地址
     * @param addressBook
     * @return
     */
    @PutMapping("/save")
    @ApiOperation("添加地址 ")
    public Result addAddress(@RequestBody AddressBook addressBook) {
        log.info("新增地址：{}",addressBook);
        addressBookService.save(addressBook);
        return  Result.success();
    }

    /**
     * 罗列用户的所以地址信息
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有地址信息")
    public Result<List<AddressBook>> list() {
        log.info("请求查询所有地址信息");
        List<AddressBook> addressBooks = addressBookService.list();
        return  Result.success(addressBooks);
    }

    /**
     * 根据id修改订单
     * @param addressBook
     * @return
     */
    @ApiOperation("更新地址")
    @PostMapping("/update")
    public Result updateAddress(@RequestBody AddressBook addressBook) {
        log.info("修改订单：{}",addressBook);
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @ApiOperation("根据id删除地址")
    @DeleteMapping("/delete")
    public Result deleteAddress(Long id) {
        log.info("删除id为{}的地址" , id);
        addressBookService.delete(id);
        return Result.success();
    }
}
