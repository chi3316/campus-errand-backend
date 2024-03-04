package com.yolo.interceptor;

import com.yolo.constant.JwtClaimsConstant;
import com.yolo.context.BaseContext;
import com.yolo.properties.JwtProperties;
import com.yolo.utility.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用来拦截请求，并进行jwt令牌校验
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断拦截到的对象是否是HandlerMethod , 否 => 放行 ， 是 => 进行jwt校验
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        //获取解析请求头中携带的token
        String token = request.getHeader(jwtProperties.getUserTokenName());

        try {
            //使用工具类解析token获得目标
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);

            //获取id
            String idValue = claims.get(JwtClaimsConstant.USER_ID).toString();
            Long userId = Long.valueOf(idValue);
            log.info("当前用户id：{}", userId);
            //放进ThreadLocal当中
            BaseContext.setCurrentId(userId);
            //3、通过，放行
            return true;
        } catch (Exception e) {
            //4、不通过，响应 401 状态码
            response.setStatus(401);
            return false;
        }

    }
}
