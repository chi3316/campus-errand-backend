package com.yolo.controller;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.vo.UserLoginVO;
import com.yolo.result.Result;
import com.yolo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户小程序端相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 微信登录
     * @return
     */
    @PostMapping("/user/login")
    @ApiOperation("用户微信登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户 : {}登录",userLoginDTO);
        UserLoginVO userLoginVO = userService.wxLogin(userLoginDTO);
        return Result.success(userLoginVO);
    }

    // No mapping for GET /user
    @GetMapping("/category/list")

    public Result list(Integer type) {
        return Result.success();
    }
}
