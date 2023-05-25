package com.itwill.spring2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyUpdateDto {

    private String replyText; // JS(reply.js)의 96줄의 객체의 키값(속성 값)과 동일하게
}
