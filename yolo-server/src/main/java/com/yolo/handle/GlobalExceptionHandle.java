package com.yolo.handle;

import com.yolo.exception.BaseException;
import com.yolo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    /**
     * 捕获业务异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandle(BaseException e) {
        log.error("异常信息：{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
