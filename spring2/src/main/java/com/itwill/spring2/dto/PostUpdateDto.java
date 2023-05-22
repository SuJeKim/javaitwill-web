package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@Getter
public class PostUpdateDto {
    
    private long id;
    private String title;
    private String content;
    
    public Post updateEntity () {
        
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        
    }
}
