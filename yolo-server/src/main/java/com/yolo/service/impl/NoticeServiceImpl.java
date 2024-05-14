package com.yolo.service.impl;

import com.yolo.mapper.NoticeMapper;
import com.yolo.pojo.vo.NoticeVO;
import com.yolo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public NoticeVO selectAll() {
        // 调用mapper , 让mapper查数据库 ， 把查到的信息封装成对象返回给controller
        noticeMapper.selectAll();
        return null;
    }
}
