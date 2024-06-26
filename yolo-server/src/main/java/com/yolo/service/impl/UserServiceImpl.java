package com.yolo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yolo.constant.JwtClaimsConstant;
import com.yolo.constant.MessageConstant;
import com.yolo.context.BaseContext;
import com.yolo.exception.LoginFailedException;
import com.yolo.mapper.UserMapper;
import com.yolo.pojo.dto.ApplyOrderTakerDTO;
import com.yolo.pojo.dto.UserLoginDTO;
import com.yolo.pojo.dto.UserUpdateDTO;
import com.yolo.pojo.entity.CheckOrderTaker;
import com.yolo.pojo.entity.User;
import com.yolo.pojo.vo.ReceiverInfoVO;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private CheckOrderTakerService checkOrderTakerService;
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
                    .isOrderTaker(-1)
                    .build();
            userMapper.insert(user);
        }

        // 为用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        // 计算过期时间
        long currentTimeStamp = System.currentTimeMillis();
        long expireTimeStamp = currentTimeStamp + jwtProperties.getUserTtl();
        // 封装VO对象并返回
        return UserLoginVO.builder()
                .id(user.getId())
                .token(token)
                .openid(openid)
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .name(user.getName())
                .expiration(expireTimeStamp)
                .isOrderTaker(user.getIsOrderTaker())
                .build();
    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO,user);
        // 获得当前用户的id，根据id修改记录
        user.setId(BaseContext.getCurrentId());
        userMapper.update(user);
    }


    @Transactional
    public void apply4OrderTaker(ApplyOrderTakerDTO applyOrderTakerDTO) {
        // 申请结单 => 把用户表的is_order_taker字段更改为1
        User user = new User();
        user.setId(BaseContext.getCurrentId());
        user.setIsOrderTaker(1);
        userMapper.update(user);
        // 得再需要一个表 check_order_taker ： id  user_id  student_id  url(上传的资料)  success
        // 插入数据到表中
        CheckOrderTaker checkOrderTaker = CheckOrderTaker.builder()
                .userId(BaseContext.getCurrentId())
                .url(applyOrderTakerDTO.getStudentCard())
                .studentId(applyOrderTakerDTO.getStudentId())
                .name(applyOrderTakerDTO.getName())
                .createTime(LocalDateTime.now())
                .build();
        checkOrderTakerService.insert(checkOrderTaker);
    }

    @Override
    public User getById(Long userId) {
        return userMapper.getById(userId);
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

    public ReceiverInfoVO getUserInfoById(Long userId) {
        User user = getById(userId);

        return ReceiverInfoVO.builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .build();
    }
}
