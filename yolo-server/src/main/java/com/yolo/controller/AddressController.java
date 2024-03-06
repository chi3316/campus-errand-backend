package com.yolo.controller;

import com.yolo.pojo.entity.AddressBook;
import com.yolo.result.Result;
import com.yolo.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/saveAddress")
    @ApiOperation("添加地址 ")
    public Result addAddress(@RequestBody AddressBook addressBook) {
        log.info("新增地址：{}",addressBook);
        addressBookService.save(addressBook);
        return  Result.success();
    }
}
