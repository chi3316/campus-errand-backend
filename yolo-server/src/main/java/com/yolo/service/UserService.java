package com.yolo.service;

import com.yolo.pojo.dto.ApplyOrderTakerDTO;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.dto.UserUpdateDTO;
import com.yolo.pojo.vo.UserLoginVO;

public interface UserService {
    UserLoginVO wxLogin(UserLoginDTO userLoginDTO);

    void update(UserUpdateDTO userUpdateDTO);

    void apply4OrderTaker(ApplyOrderTakerDTO applyOrderTakerDTO);
}
