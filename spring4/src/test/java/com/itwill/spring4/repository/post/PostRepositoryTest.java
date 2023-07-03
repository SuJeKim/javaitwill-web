package com.itwill.spring4.repository.post;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring4.dto.PostUpdateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    
    //@Test
    public void testFindAll() {
        List<Post> list = postRepository.findByOrderByIdDesc();
        for (Post p : list) {
            log.info(p.toString());
        }
    }
    
   // @Test
    public void testSave() {
        // DB 테이블에 insert할 레코드(엔터티)를 생성:
        Post entity = Post.builder()
                .title("JPA 테스트")
                .content("JPA 라이브러리를 사용한 INSERT")
                .author("admin")
                .build();
        log.info("insert 전: {}", entity);
        log.info("created: {}, modified: {}", entity.getCreatedTime(), entity.getModifiedTime());
        
        // DB 테이블에 insert: 
        postRepository.save(entity);
        //-> save 메서드는 테이블에 삽입할 엔터티를 파라미터에 전달하면,
        //   테이블에 저장된 엔터티 객체를 리턴.
        //-> 파라미터에 전달된 엔터티 필드들을 변경해서 리턴.
        
        log.info("insert 후: {}", entity);
        log.info("created: {}, modified: {}", entity.getCreatedTime(), entity.getModifiedTime());
    }
    
    // @Test
    // @Transactional
    public void update() {
        // 업데이트하기 전의 엔터티 검색:
        // 검색은 리턴 결과가 존재 할수도 없을 수도 있기에 Post 타입이 아닌 Optional로 지정함. 
        // Post라는 타입을 꺼내주는 메서드: orElseThrow -값이 있으면 리턴 없으면 예외(오류)를 던져 버림.
        Post entity = postRepository.findById(61L)
                .orElseThrow();
        log.info("update 전: {}", entity);
        log.info(" update 전 수정 시간: {}", entity.getModifiedTime());
        
        // entity를 변경할 내용을 가지고 있는 객체 생성:
        PostUpdateDto dto = new PostUpdateDto();
        dto.setTitle("JPA update 테스트");
        dto.setContent("JPA Hibernate를 사용한 DB 테이블 업데이트");
        
        // entity를 수정:
        entity.update(dto);
        
        // DB 테이블 업데이트:
        // JPA에서는 insert와 update 메서드가 구분되어 있지 않음.
        // save() 메서드의 argument가 DB에 없는 entity이면 insert, DB에 있는 entity이면 update를 실행.
        // save: 커밋되는 순간이 시간 차이가 존재
        // saveAndFlush: 변경 사항 즉각 반응됨. 바로 커밋.
        postRepository.saveAndFlush(entity);
    }
    
    @Test
    public void testDelete() {
        // DB 테이블의 행의 개수(앤터티 개수) 리턴.
        long count = postRepository.count();
        log.info("삭제 전 count = {}", count);
        
        postRepository.deleteById(21L);
        
        count = postRepository.count();
        log.info("삭제 후 count = {}", count);
    }
    
    
}
