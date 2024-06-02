package com.yolo.controller;

import com.yolo.pojo.dto.AddressBookDTO;
import com.yolo.pojo.entity.AddressBook;
import com.yolo.pojo.vo.AddressVO;
import com.yolo.result.Result;
import com.yolo.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
     * 向地址簿中添加地址
     * @param addressBookDTO
     * @return
     */
    @PutMapping("/save")
    @ApiOperation("添加地址 ")
    public Result<String> addAddress(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("新增地址：{}",addressBookDTO);
        addressBookService.save(addressBookDTO);
        return  Result.success();
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址")
    public Result<AddressVO> getAddressById(@PathVariable Long id) {
        log.info("查询地址：{}",id);
        AddressVO addressVO = addressBookService.getById(id);
        return Result.success(addressVO);
    }

    /**
     * 罗列用户的所有地址信息
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有地址信息")
    public Result<List<AddressVO>> list() {
        log.info("请求查询所有地址信息");
        List<AddressVO> addressVOS = addressBookService.list();

        return  Result.success(addressVOS);
    }

    /**
     * 根据id修改地址
     * @param addressBook
     * @return
     */
    @ApiOperation("更新地址")
    @PostMapping("/update")
    public Result<String> updateAddress(@RequestBody AddressBook addressBook) {
        log.info("修改地址：{}",addressBook);
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    @ApiOperation("根据id删除地址")
    @DeleteMapping("/delete")
    public Result<String> deleteAddress(Long id) {
        log.info("删除id为{}的地址" , id);
        addressBookService.delete(id);
        return Result.success();
    }

    /**
     * 设置默认地址
     * @param addressBook
     * @return
     */
    /*
    @PostMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefault(@RequestBody AddressBook addressBook) {
        log.info("设置{} 为默认地址" , addressBook);
        addressBookService.setDefault(addressBook);
        return Result.success();
    }
     */
}
