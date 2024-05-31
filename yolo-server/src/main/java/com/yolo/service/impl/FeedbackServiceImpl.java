package com.yolo.service.impl;

import com.yolo.context.BaseContext;
import com.yolo.mapper.AdviceMapper;
import com.yolo.mapper.AdviceUrlsMapper;
import com.yolo.pojo.dto.FeedbackDTO;
import com.yolo.pojo.entity.Advice;
import com.yolo.pojo.entity.AdviceUrls;
import com.yolo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private AdviceMapper adviceMapper;
    @Autowired
    private AdviceUrlsMapper adviceUrlsMapper;

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        Advice advice = new Advice();
        // 把DTO里的文本拿出来
        advice.setText(feedbackDTO.getText());
        // 添加用户id 到 Advice
        advice.setUserId(BaseContext.getCurrentId());
        // 插入到表中，返回自增的advice id
        adviceMapper.save(advice);
        Long adviceId = advice.getId();

        // 开启事务，同时写入数据库
        // 把urls拿出来,insert n次到advice_urls表中
        List<String> urls = feedbackDTO.getUrls();
        for(String url : urls){
            AdviceUrls adviceUrls = new AdviceUrls(adviceId,url);
            adviceUrlsMapper.save(adviceUrls);
        }
    }
}
