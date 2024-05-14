package com.yolo.controller;

import com.yolo.pojo.vo.NoticeVO;
import com.yolo.result.Result;
import com.yolo.service.NoticeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "通知栏相关接口")
@RestController
@RequestMapping("/user/notice")
@Slf4j
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    /**
     * 获取所有公告信息
     * @return
     */
    @GetMapping("/selectAll")
    public Result<NoticeVO> selectAll() {
        // controller -> service -> mapper -> 数据库
        noticeService.selectAll();
        return null;
    }
}
