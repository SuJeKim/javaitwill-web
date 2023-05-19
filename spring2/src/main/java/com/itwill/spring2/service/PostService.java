package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// application-context.xml에서 <context:component-scan> 설정에서 
// com.itwill.spring2.service 패키지와 그 하위 패키지를 스캔(검색)하고 있음.
@Service  //-> 스프링 컨테이너에서 서비스 컴포넌트 객체를 생성하고 관리(필요한 곳에 주입).

// PostService(PostRepositiry)를 자동으로 만들어줌.
@RequiredArgsConstructor // 2. (2) final로 선언된 필드를 초기화하는 생성자.
@Slf4j
public class PostService {

    // 1. @Autowired
    // 2. 아래가 또다른 방법 - final 변수는 반드시 초기화 필! -> argument를 갖는 생성자 필
    //  ==> 생성자에 의한 의존성 주입
    
    /* 의존성 주입(DI: Dependency Injection):
     * 1. 필드에 의한 의존성 주입 - @Autowired 애너테이션 사용.
     * 2. 생성자에 의한 의존성 주입
     *      (1) 필드를 final로 선언
     *      (2) final 변수를 초기화할 수 있는 생성자를 작성.
     *  
     * ==> 2중 하나만 사용하기     
     */
    
    // @Autowired private PostRepository postRepository; => 1. 필드에 의한 의존성 주입
    
    private final PostRepository postRepository; // 2. (1) 생성자에 의한 의존성 주입
    
    // 포스트 목록 페이지
    public List<PostListDto> read() {
        log.info("read()");
        
        List<Post> list =  postRepository.selectOrderByIdDesc();
        
        
//        List<PostListDto> result = new ArrayList<>();
//        for (Post p : list) {
//            PostListDto dto = PostListDto.fromEntity(p);
//            result.add(dto);
//        }
//        return result;
        // 간단하게 만듬: 57줄
        
        return list.stream().map(PostListDto::fromEntity).toList(); // 익명 내부 클래스: 람다 표현식 + list가 하나씩 PostListDto로 넘어감. 반복문.
    }
    
    // 포스트 상세보기 페이지
    public Post read(long id) {
        log.info("read(id = {})", id);
        
        return postRepository.selectById(id);
    }
    
    // 새 포스트 작성 페이지
    public int create(PostCreateDto dto) {
        log.info("create({})", dto);
        
        // PostCreateDto 타입을 Post로 변환해서(서로의 타입을 변경함)
        // 리포지토리 계층의 메서드를 호출 - DB insert. 
        return postRepository.insert(dto.toEntity()); 
    }
    
    // 포스트 업데이트
    public int update(Post post) {
        log.info("update({})", post);
        
        return postRepository.updateTitleAndContent(post);
    }
    
    // 포스트 삭제
    public int delete(long id) {
        log.info("delete({})", id);
        
        return postRepository.deleteById(id);
    }
}
