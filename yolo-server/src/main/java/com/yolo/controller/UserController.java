package com.yolo.controller;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.vo.UserLoginVO;
import com.yolo.result.Result;
import com.yolo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 微信登录
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户{}登录",userLoginDTO);
        UserLoginVO userLoginVO = userService.wxLogin(userLoginDTO);
        return Result.success(userLoginVO);
    }
}
