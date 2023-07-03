package com.itwill.spring4.repository.post;

import com.itwill.spring4.dto.PostUpdateDto;
import com.itwill.spring4.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString

@Entity // JPA 엔터티 클래스 - 데이터베이스 테이블과 매핑되는 클래스.

@Table(name = "POSTS")
// 엔터티 클래스 이름이 데이터 베이스 테이블 이름과 다른 경우
@SequenceGenerator(name = "POSTS_SEQ_GEN", sequenceName = "POSTS_SEQ", allocationSize = 1)
// 모든 엔터티 클래스는 반드시 id 속성이 있어야 함.
// -> id 속성이 sequence 되는 것을 의미함. + 다음 @SequenceGenerator를 작성하면 테이블과 연동하여 확인함(테이블 이름, 열 이름, 조건 등등).
// -> allocationSize: 1씩 증가함. 문서상에서는 50이라고 정의가 되어있음.
// strategy 생성 타입이 SEQUENCE인 전략을 세우는데 이는 POSTS_SEQ_GEN이를 통해서 생성하고, sequence되는 이름은 POSTS_SEQ다.
public class Post extends BaseTimeEntity{

    @Id // Primary key 제약조건
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTS_SEQ_GEN")
    private Long id;
    
    @Column(nullable = false) // Not Null 제약조건
    private String title;
    
    @Column(nullable = false) 
    private String content;

    @Column(nullable = false) 
    private String author;
    
    // Post entity의 title과 content를 수정해서 리턴하는 메서드:
    public Post update(PostUpdateDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        
        return this;
    }
    
}
