package com.yolo.controller;

import com.yolo.constant.MessageConstant;
import com.yolo.pojo.dto.ApplyOrderTakerDTO;
import com.yolo.pojo.dto.FeedbackDTO;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.dto.UserUpdateDTO;
import com.yolo.pojo.vo.ReceiverInfoVO;
import com.yolo.pojo.vo.UserLoginVO;
import com.yolo.result.Result;
import com.yolo.service.FeedbackService;
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
    @Autowired
    private FeedbackService feedbackService;

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

    /**
     * 更新用户的信息
     * @param userUpdateDTO
     * @return
     */
    @PostMapping("/user/update")
    @ApiOperation("更新用户信息")
    public Result<String> update(@RequestBody UserUpdateDTO userUpdateDTO) {
        log.info("更新用户信息：{}",userUpdateDTO);
        userService.update(userUpdateDTO);
        return Result.success();
    }

    /**
     * 添加用户建议
     * @param feedbackDTO
     * @return
     */
    @PostMapping("/user/addAdvice")
    @ApiOperation("添加用户反馈")
    public Result<String> addAdvice(@RequestBody FeedbackDTO feedbackDTO) {
        log.info("添加用户反馈：{}" , feedbackDTO);
        feedbackService.save(feedbackDTO);
        return Result.success();
    }

    /**
     * 用户申请成为接单员
     * @param applyOrderTakerDTO
     * @return
     */
    @ApiOperation("用户申请接单")
    @PostMapping("/user/applyOrderTaker")
    public Result<String> applyOrderTaker(@RequestBody ApplyOrderTakerDTO applyOrderTakerDTO) {
        log.info("用户申请接单：{}" , applyOrderTakerDTO);
        userService.apply4OrderTaker(applyOrderTakerDTO);
        return Result.success();
    }

    @ApiOperation("根据接单人ID获取接单人信息")
    @GetMapping("/user/getReceiverInfo/{id}")
    public Result<ReceiverInfoVO> getReceiverInfo(@PathVariable Long id) {
        log.info("接单人id：{}", id);
        try {
            ReceiverInfoVO receiverInfo = userService.getUserInfoById(id);
            return Result.success(receiverInfo);
        } catch(Exception e) {
            log.error("获取接单人信息失败，接单人ID：{}", id, e);
            return Result.error(MessageConstant.GET_RECEIVER_INFO_FAILED);
        }
    }
    @ApiOperation("判断是否有接单资格")
    @GetMapping("/user/getIsReceiver/{id}")
    public Result<Integer> getIsReceiver(@PathVariable Long id) {
        log.info("用户id：{}", id);
        try {
            Integer isReceiver = userService.getIsReceiver(id);
            return Result.success(isReceiver);
        } catch(Exception e) {
            log.error("获取用户信息失败，用户ID：{}", id, e);
            return Result.error(MessageConstant.GET_RECEIVER_INFO_FAILED);
        }
    }
}
