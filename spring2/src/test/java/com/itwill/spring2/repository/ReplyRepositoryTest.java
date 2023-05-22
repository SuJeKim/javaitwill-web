package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Reply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
)
// file에 myBiats 설정 정보 존재.
// test클래스는 기본 생성자만 가짐.
// test는 dispatcherServlet이 sql 구현은 myBatis가
public class ReplyRepositoryTest {
    
    // Repository 객체를 주입받음(의존성 주입, DI)
    // 생성자에 의한, 필드에 의한 2개의 종류의 의존성 주입
    @Autowired
    private ReplyRepository replyRepository;
    //  org.apache.ibatis.binding.MapperProxy@16e7b402
    //  ->인터페이스를 구현한 클래스가 만들어져 있다.
    
    @Test
    public void testSelectReplyCountWithPostId() {
        long result = replyRepository.selectReplyCountWithPostId(3);
        
        log.info("result = {}",result);
    }
    
    
//    @Test
    public void testDelete() {
        int result = replyRepository.delete(1);
        
        log.info("result = {}", result);
    }
    
    
//    @Test
    public void testUpdate() {
        Reply entity = Reply.builder()
                .id(1)
                .reply_text("댓글 수정 test")
                .build();
        
        int result = replyRepository.update(entity);
        log.info("result = {}", result );
    }

    
//    @Test
    public void testInsert() {
        Reply entity = Reply.builder()
                .reply_text("부럽다")
                .writer("2시간 남았따...")
                .post_id(4)
                .build();
        
        int result = replyRepository.insert(entity);
        log.info("result = {}", result);
    }
 
    
//    @Test
    // test 메서드는 리턴 타입이 void이고, public.
    public void test() {
        Assertions.assertNotNull(replyRepository);
        log.info(replyRepository.toString());
        
        List<Reply> list = replyRepository.selectByPostId(21);
        for (Reply reply: list) {
            log.info(reply.toString());
        }
    }

}
