package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
)
public class RepositoryTest {

    @Autowired
    private PostRepository postRepository; // PostRepository는 추상메서드임. 그런데 객체가 생성이 됨.
    
    // test를 호출하는 것이 스프링 프레임워크인 거고 애가 JUnit 테스트르 부름
    
    @Test
    public void testDelete() {
        int result = postRepository.deleteById(2);
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
    
    
   // @Test
    public void testUpdate() {
        Post post = Post.builder()
                .id(41) // 업데이트할 포스트 아이디
                .title("업데이트 TEST") // 업데이트할 제목
                .content("MyBatis 프레임워크를 사용한 DB 업데이트") //업데이트할 내용.
                .build();
        int result = postRepository.updateTitleAndContent(post);
        Assertions.assertEquals(1, result);
    }
    
    
    
    
    
    //@Test
    public void testselectById() {
        Post result = postRepository.selectById(5);
        Assertions.assertNotNull(result);
        log.info(result.toString());
        
        result = postRepository.selectById(-1);
        Assertions.assertNull(result);
        
    }
    
    
    //@Test
    public void testSelectOrderByIdDesc() {
        List<Post> list = postRepository.selectOrderByIdDesc();
        for (Post p: list) {
            log.info(p.toString());
        }
                
    }
    
    // @Test
    public void testPostRepository() {
        Assertions.assertNotNull(postRepository);
        log.info("postRepository = {}", postRepository);
        
        Post post = Post.builder().title("Lombok/MyBatis 테스트").content("MyBatis 이용한 insert, Lombok 라이브러리를 이용한 Builder 패턴 구현").author("admin").build();
        log.info(post.toString());
        
        int result = postRepository.insert(post);
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
        
    }
    
}
