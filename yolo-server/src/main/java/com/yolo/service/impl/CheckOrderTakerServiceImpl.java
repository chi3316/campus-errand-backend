package com.yolo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yolo.constant.JwtClaimsConstant;
import com.yolo.constant.MessageConstant;
import com.yolo.context.BaseContext;
import com.yolo.exception.LoginFailedException;
import com.yolo.mapper.CheckOrderTakerMapper;
import com.yolo.mapper.UserMapper;
import com.yolo.pojo.dto.ApplyOrderTakerDTO;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.dto.UserUpdateDTO;
import com.yolo.pojo.entity.CheckOrderTaker;
import com.yolo.pojo.entity.User;
import com.yolo.pojo.vo.UserLoginVO;
import com.yolo.properties.JwtProperties;
import com.yolo.properties.WeChatProperties;
import com.yolo.service.CheckOrderTakerService;
import com.yolo.service.UserService;
import com.yolo.utility.HttpClientUtil;
import com.yolo.utility.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class CheckOrderTakerServiceImpl implements CheckOrderTakerService {
    @Autowired
    private CheckOrderTakerMapper checkOrderTakerMapper;

    @Override
    public void insert(CheckOrderTaker checkOrderTaker) {
        checkOrderTakerMapper.insert(checkOrderTaker);
    }
}
