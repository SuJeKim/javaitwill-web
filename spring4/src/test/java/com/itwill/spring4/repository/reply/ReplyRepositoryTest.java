package com.itwill.spring4.repository.reply;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.post.PostRepository;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    // 댓글 번호로 검색하기
   // @Test
    public void testFindById() {
        
        // 11: integer type -> L 붙여야 함.
        // select * from replies where id = 11;
        //-> 다음 쿼리문이 아닌 이유:
        /*
         * entity 클래스에서 관계형 데이터 베이스에서 관계를 가지고 있다고 entity를 정의하면 기본적으로 join문장을 사용함.
         * (예) @ManyToOne
         */
       
        
        Reply reply =  replyRepository.findById(11L).orElseThrow();
        log.info(reply.toString());
       // log.info(reply.getPost().toString());
        
        /*
         * findById() 메서드는
         * reply entity에서 FetchType.EAGER를 사용한 경우에는 join 문장을 실행
         * FetchType.LAZY를 사용한 경우에는 단순 select 문장을 실행하고, 
         * Post 엔터티가 필요한 경우에 (나중에) join 문장이 실행됨.
         * 
         * 현업에서는 보통 FetchType.LAZY을 사용.
         */
    }
    
    @Test
    public void testFindByPost() {
        // 포스트 아이디로 포스트 1개를 검색:
        Post post = postRepository.findById(7L).orElseThrow();
        
        // 해당 포스트에 달린 모든 댓글 검색:
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        for(Reply r : list) {
            log.info(r.toString());
        }
    }
    
    
}
