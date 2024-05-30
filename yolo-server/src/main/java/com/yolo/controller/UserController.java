package com.yolo.controller;

import com.yolo.constant.MessageConstant;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.vo.UserLoginVO;
import com.yolo.result.Result;
import com.yolo.service.UserService;
import com.yolo.utility.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户小程序端相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 微信登录
     *
     * @return
     */
    @PostMapping("/user/login")
    @ApiOperation("用户微信登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户 : {}登录", userLoginDTO);
        UserLoginVO userLoginVO = userService.wxLogin(userLoginDTO);
        return Result.success(userLoginVO);
    }

    @PostMapping("/common/upload")
    @ApiOperation("上传文件")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);
        try {
            String originalFilename = file.getOriginalFilename();
            // 截取原始文件名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 构造新文件的名称
            String objectName = UUID.randomUUID() + extension;
            // 文件请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.info("文件上传失败，{}", file);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
