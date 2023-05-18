package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 맴버 변수가 존재하는 생성자.
@Builder // 내부 클래스 생성- 맴버 변수(Post 클래스의 맴버 변수와 동일)
@Getter
@Setter
@ToString
public class Post {
    
    private long id;
    private String title; 
    private String content; 
    private String author;
    private LocalDateTime created_time;
    private LocalDateTime modified_time; // sql의 테이블의 col 이름과 동일하게 하기

}
