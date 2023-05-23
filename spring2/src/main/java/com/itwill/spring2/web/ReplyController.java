package com.itwill.spring2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 변수 초기화
@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;
    
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
