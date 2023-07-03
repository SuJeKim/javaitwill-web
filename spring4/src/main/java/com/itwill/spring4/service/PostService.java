package com.itwill.spring4.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring4.dto.PostCreateDto;
import com.itwill.spring4.dto.PostSearchDto;
import com.itwill.spring4.dto.PostUpdateDto;
import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.post.PostRepository;

import jakarta.transaction.TransactionScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    // 생성자를 사용한 의존성 주입:
    // 변경 안됨.
    private final PostRepository postRepository;

    // DB POSTS 테이블에서 전체 검색한 결과를 리턴:
    @Transactional(readOnly = true)
    // 읽기만 하고 수정을 안 하기에 즉, 읽기 전용 용도이기에 Select 속도를 빠르게 하기 위해서 애너테이션 설정.
    public List<Post> read() {
        log.info("read()");

        return postRepository.findByOrderByIdDesc();
    }

    // DB POSTS 테이블에 엔터티를 삽입(insert):
    public Post create(PostCreateDto dto) {
        log.info("create(dto ={}", dto);

        // DTO를 Entity로 변환:
        Post entity = dto.toEntity();
        log.info("save 전 entity={}", entity);

        // DB 테이블에 저장(insert)
        postRepository.save(entity);
        log.info("save 후 entity={}", entity);

        return entity;
    }

    // id별 검색
    @Transactional(readOnly = true)
    public Post read(Long id) {
        log.info("read(id = {})", id);

        return postRepository.findById(id).orElseThrow();
    }

    // id별 삭제
    public void delete(Long id) {
        log.info("delete(id = {})", id);

        postRepository.deleteById(id);
    }

    // id별 수정
    // Junit Test와는 다름.
    // readOnly = false(기본값): select 과정이 늦을 수도 있음. 변경되고 있는지를 추적하고 관리하며, 변경시 DB와 연동이
    // 됨.
    // readOnly = true: 읽기만 하고, DB에 수정 변경 안됨.
    @Transactional // (1)
    public void update(PostUpdateDto dto) {
        log.info("update(dto ={})", dto);

        // (1) 메서드에 @Transactional 애너테이션을 설정하고,
        // (2) DB에서 entity를 검색하고,
        // (3) 검색한 엔터티를 수정하면,
        // 틀랙잭션이 끝나는 시점에 DB update가 자동으로 수행됨!

        // 실제로 존재하는 entity의 경우에는 오류를 날리지 않고 있다고 알림.
        Post entity = postRepository.findById(dto.getId()).orElseThrow(); // (2)
        entity.update(dto); // (3)
    }

    @Transactional(readOnly = true)
    public List<Post> search(PostSearchDto dto) {
        log.info("search(dto ={})", dto);

        List<Post> list = null;
        switch (dto.getType()) {
        case "t":
            list = postRepository.findByTitleContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        case "c":
            list = postRepository.findByContentContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        case "tc":
            list = postRepository.searchByKeyword(dto.getKeyword());
            break;
        case "a":
            list = postRepository.findByAuthorContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        }

        return list;
    }

}
