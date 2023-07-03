package com.itwill.spring4.repository.reply;

import com.itwill.spring4.dto.reply.ReplyUpdateDto;
import com.itwill.spring4.repository.BaseTimeEntity;
import com.itwill.spring4.repository.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @NoArgsConstructor, @Getter는 entity 클래스에서 필수 요소
@NoArgsConstructor
@Getter
// 필드 post를 제외하고 toString을 만들어 줌
@ToString(exclude = {"post"})
@AllArgsConstructor
@Builder

@Entity
@Table(name = "REPLIES")
@SequenceGenerator(name = "REPLIES_SEQ_GEN", sequenceName = "REPLIES_SEQ", allocationSize = 1)
public class Reply extends BaseTimeEntity{
    
    @Id
    @GeneratedValue(generator = "REPLIES_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    private Long id; // Primary Key
    
    // 관계 애너테이션 설명:
    // 클래스를 기준으로 생각
    // 여러개의 reply가 한 개의 post에 달릴 수 있음.
    // To 앞에 있는게 reply 클래스
    // Many: Reply 클래스
    // One: Post 클래스
    
    // fetch: 데이터를 가지고 오는 행위
    // fetch의 타입:
 // EAGER(기본값): 즉시 로딩, LAZY: 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY) 
    private Post post; // Foreign Key, 관계를 맺고 있는 entity
    
    @Column(nullable = false) // Not Null
    private String replyText; // 댓글 내용
    
    @Column(nullable = false)
    private String writer; // 댓글 작성자
    
    // setter()가 없어서 만듦
    // 댓글 내용을 수정하고, 수정된 엔터티를 리턴하는 메서드:
    public Reply update(String replyText) {
        this.replyText = replyText;
        
        return this;
    }

}
