package com.itwill.spring2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.domain.Reply;
import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.dto.ReplyReadDto;
import com.itwill.spring2.dto.ReplyUpdateDto;
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
    
    /*
     * ResponseEntity:
     * 클라이언트에게 전송될 HTTP 응답
     * ->  응답 본문, 상태 코드, 헤더 등을 포함하는 HTTP 응답을 캡슐화
     * -> 개발자는 세부적인 응답 설정을 조작하고, 다양한 상태 코드 및 헤더를 설정하여 클라이언트에게 전송
     * 
     */
    
    // REST: RequestMapping & RequestMethod에서 PostMapping은 create
    
 
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
    
    @GetMapping("/all/{postId}") // 요청주소의 변수 표현: {} -> 선언시 param과 동일하게 맞추기. -> dispatcherServlet이 해당 주소의 아이디를 param에 넣어줌.
    /**
     * 
     * @param postId
     * @return Reply들을 저장하고 있는 객체를 리턴.
     */
    public ResponseEntity<List<ReplyReadDto>> read(@PathVariable long postId) { 
        log.info("read(postId= {})", postId);
        
        List<ReplyReadDto> list = replyService.read(postId);
        
        // 댓글 개수 확인
        log.info("# of replies = {}", list.size());
        
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReplyReadDto> readById(@PathVariable long id) {
        log.info("readbyId(id={})", id);
        
        ReplyReadDto dto = replyService.readById(id);
        log.info("dto = {}",dto);
        
        return ResponseEntity.ok(dto);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteReply(@PathVariable long id) {
        log.info("deleteReply(id = {})",id);
        
        int result = replyService.delete(id);
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 
     * @param id
     * @param dto JS에서 선언한(96 줄) data의 {replyText : replyText} 처리, update를 할 내용.
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateReply(@PathVariable long id, @RequestBody ReplyUpdateDto dto ) {
        log.info("updateReply(id = {}, dto = {})", id, dto);
        
        int result = replyService.update(id, dto);
        
        return ResponseEntity.ok(result);
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
