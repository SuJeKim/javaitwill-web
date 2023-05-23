package com.itwill.spring2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@RequiredArgsConstructor // final 변수 초기화
@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;
    
    @PostMapping
    public ResponseEntity<Integer> createReply(@RequestBody ReplyCreateDto dto) { // @RequestBody 안에 ReplyCreateDto dto이 존재.
        log.info("createReply(dto = {})", dto);
        
        int result = replyService.create(dto) ;
        
//        ResponseEntity.status(500).build(); // httpStatusCode를 보내서 에러 처리
        
        return ResponseEntity.ok(result); 
        // ok: 200번 성공 코드, 데이터 1을 리턴(클라이언트에게 보냄).
        // 데이터의 값을 보고 1인 경우에만 응답을 보내는 것으로  해야 함.
        // null: 실패가 아니라 데이터가 없다는 것임. 즉, 성공한 것. 브라우저에 네트워트를 보면 200이라고 뜨니까
    }
    
    // test클래스를 외부 패키지에서 알 수 있게.
//    @AllArgsConstructor
//    @Data
//    public class Test {
//        private int age;
//        private String name;
//      
//    }
    
    /*
     *  @GetMapping
     *   public String hello() {
     *    log.info("hello");
     *    return "hello";
     *   }
     */
    
    
//    @GetMapping
//    public int hello() {
//        log.info("hello");
//        return 1;
//    }
    
//    @GetMapping
//    public Test hello() {
//        log.info("hello()");
//        return new Test(16, "test");
//    }
    
    
    
}
