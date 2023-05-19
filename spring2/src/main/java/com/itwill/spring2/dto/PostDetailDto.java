package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder // 빌더 패턴을 만들을려면 11줄이 필요.
public class PostDetailDto {
    
    private long id;
    private String title;
    private String content;
    private String author;
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    // Post 타입 객체를 PostDetailDto 타입으로 변환해서 리턴.
    // dto가 만들어지기 전에 메서드를 호출 필
    public static PostDetailDto fromEntity(Post entity) {
        return PostDetailDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdTime(Timestamp.valueOf(entity.getCreated_time()))
                .modifiedTime(Timestamp.valueOf(entity.getModified_time()))
                .build();
    }
}
