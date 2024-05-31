package com.yolo.service;

import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.vo.UserLoginVO;

public interface UserService {
    UserLoginVO wxLogin(UserLoginDTO userLoginDTO);
}
