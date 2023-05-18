package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그
@RequiredArgsConstructor // 생성자에 의한 의존성 주입
@RequestMapping("/post")  // PostController 클래스의 메서드들은 요청 주소가 "/post"로 시작. -> 메서드는 하위 주소만 적어도 됨.
// 매핑 방식 지정을 할 경우엔 controller class가 가지고 있는 메서드에서만 
@Controller // DispatcherServlet에게 controller 컴포넌트로 등록.
public class PostController {

    private final PostService postService; // 생성자에 의한 의존성 주입.
    
    @GetMapping("/list") // Get 방식의 /post/list 요청 주소를 처리하는 메서드 
    public void list() {
        log.info("list()");
        // 리턴 값이 없는 경우 뷰의 이름은 요청 주소와 동일하다. -> servlet-context.xml에서 확인하기.
        // /WEB-INF/views/post/list.jsp
    }
    
}
