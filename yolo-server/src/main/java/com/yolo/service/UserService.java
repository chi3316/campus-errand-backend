package com.yolo.service;

import com.yolo.pojo.dto.ApplyOrderTakerDTO;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.dto.UserUpdateDTO;
import com.yolo.pojo.entity.User;
import com.yolo.pojo.vo.ReceiverInfoVO;
import com.yolo.pojo.vo.UserLoginVO;

public interface UserService {
    UserLoginVO wxLogin(UserLoginDTO userLoginDTO);

    void update(UserUpdateDTO userUpdateDTO);

    void apply4OrderTaker(ApplyOrderTakerDTO applyOrderTakerDTO);

    User getById(Long userId);

    ReceiverInfoVO getUserInfoById(Long userId);

    Integer getIsReceiver(Long userId);
}
