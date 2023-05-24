package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder  //-> @AllArgsConstructor 필수. DB -> DTO
public class ReplyReadDto {

    private long id;
    private long postId;
    private String replyText;
    private String writer;
    private Timestamp modifiedTime; 
    
    // static: 맴버 변수가 만들어지기 전에 dto 타입의 변수를 만들기 위해
    // DB에서 select한 Reply 타입 객체를 ReplyReadDto 타입 객체로 변환해서 리턴.
    public static ReplyReadDto fromEnetity(Reply entity) {
        
        return ReplyReadDto.builder()
                .id(entity.getId())
                .postId(entity.getPost_id())
                .replyText(entity.getReply_text())
                .writer(entity.getWriter())
                .modifiedTime(Timestamp.valueOf(entity.getModified_Time()))
                .build();
    }
    
}
