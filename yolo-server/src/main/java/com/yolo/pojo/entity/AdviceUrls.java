package com.yolo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdviceUrls implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long adviceId;
    private String url;

    public AdviceUrls(Long adviceId, String url) {
        this.adviceId = adviceId;
        this.url = url;
    }
}
