package com.yolo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yolo.constant.JwtClaimsConstant;
import com.yolo.constant.MessageConstant;
import com.yolo.exception.LoginFailedException;
import com.yolo.mapper.UserMapper;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.entity.User;
import com.yolo.pojo.vo.UserLoginVO;
import com.yolo.properties.JwtProperties;
import com.yolo.properties.WeChatProperties;
import com.yolo.service.UserService;
import com.yolo.utility.HttpClientUtil;
import com.yolo.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    // 微信接口的地址以及参数
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String AUTHORIZATION_CODE = "authorization_code";
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 实现用户微信登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public UserLoginVO wxLogin(UserLoginDTO userLoginDTO) {
        // 调用微信提供的接口返回 openid
        String openid = getOpenId(userLoginDTO.getCode());
        if (null == openid) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        // 判断是否为新用户，新用户就自动注册 insert
        User user = userMapper.getByOpenid(openid);
        if(null == user) {
            // 这里得继续使用这个user,因为新用户执行insert之后生成的主键会写入user的id属性,需要利用这个id生成令牌
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        // 为用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        // 封装VO对象并返回
        return UserLoginVO.builder()
                .id(user.getId())
                .token(token)
                .openid(openid)
                .build();
    }

    /**
     * 调用微信的接口，获取用户的openid
     * @param code
     * @return
     */
    private String getOpenId(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type",AUTHORIZATION_CODE);

        // 利用HttpClient发送请求
        String json = HttpClientUtil.doGet(WX_LOGIN_URL, map);
        // 解析json
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
