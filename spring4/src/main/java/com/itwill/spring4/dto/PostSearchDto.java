package com.itwill.spring4.dto;

import lombok.Data;

@Data
public class PostSearchDto {

    // reqeustParameter와 동일한 이름 사용하기.
    private String type;
    private String keyword;
}
