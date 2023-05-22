package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@AllArgsConstructor
//@Builder
//@Getter
//@ToString

// 댓글 개수 추가 후 만든 것
@Data
@NoArgsConstructor
@AllArgsConstructor // builder 패턴을 위해
@Builder //  builder 패턴을 위해

/**
 * 읽기 전용
 * PostList를 전체 다 출력이 아닌 부분만 출력. 
 * 
 * @author ITWILL
 *
 */
public class PostListDto {
    
    private long id;
    private String title;
    private String author;
    private Timestamp createdTime;  // JStl 포맷 태그는 LocalDateTime을 처리하지 못함.(model/domain에서는 상관 없음)
    // JSTL에서는 LocalDateTime 객체를 사용하지 못하기 떄문에 Timestamp 타입으로 선언.
    
    private long rcnt; // 댓글 개수
    
    // Post 타입의 객체를 PostListDto 타입의 객체로 변환해서 리턴하는 메서드
    // 맴버변수를 Post타입을 가져와서 세팅.
    public static PostListDto fromEntity (Post entity) {
        return PostListDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .createdTime(Timestamp.valueOf(entity.getCreated_time()))
                .build();
    }
    
    
}
