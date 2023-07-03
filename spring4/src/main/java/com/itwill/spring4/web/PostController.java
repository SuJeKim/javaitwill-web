package com.itwill.spring4.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring4.dto.PostCreateDto;
import com.itwill.spring4.dto.PostSearchDto;
import com.itwill.spring4.dto.PostUpdateDto;
import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.post.PostRepository;
import com.itwill.spring4.repository.reply.Reply;
import com.itwill.spring4.repository.reply.ReplyRepository;
import com.itwill.spring4.service.PostService;
import com.itwill.spring4.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final ReplyService replyService;
    private final PostRepository postRepository;

    @GetMapping
    public String read(Model model) {
        log.info("read()");

        List<Post> list = postService.read();

        // Model에 검색 결과를 세팅:
        model.addAttribute("posts", list);

        return "/post/read"; // 뷰에 이름을 리턴.

    }

    // spring EL: 메서드 호출할 때 '' 사용.
    // .hasRole("USER") [SecurityConfig.java] == "hasRole('USER')"
    // 요청이 들어왔을 때, 메서드 실행 전에 로그인 필터(hasRole('USER'))를 통해 거름
    @PreAuthorize("hasRole('USER')") // 페이지 접근 이전에 인증(권한, 로그인) 여부를 확인.
    @GetMapping("/create")
    public void create() {
        log.info("create() GET");

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.info("create(dto ={}) POST", dto);

        // form에서 submit(제출)된 내용을 DB 테이블에 insert
        postService.create(dto);

        // DB 테이블 insert 후 포스트 목록 페이지로 redirect 이동.
        return "redirect:/post";
    }

    // path variable: 매핑 주소에서 "details/{}"
    // 쿼리 스트링은 argument로, 쿼리 스트링 == 파라미터 이름
    // 문자열들의 배열의 요청 주소를 매핑 가능함.
    // -> "post/details", "/post/modify" 요청 주소들을 처리하는 컨트롤러 메서드
    // -> 뷰는 매핑주소가 details로 들어올 경우 다음 매핑을 찾음. 그 반대도 마찬가지.
    // -> 리턴 값이 만약 String이면 각각 retrun을 지정해야 함.
    @PreAuthorize("hasRole('USER')")
    @GetMapping({ "/details", "/modify" })
    public void read(Long id, Model model) {
        log.info("read(id = {})", id);

        // POSTS 테이블에서 id에 해당하는 포스트를 검색.
        Post post = postService.read(id);

        // 결과를 model에 저장 -> 뷰로 전달됨.
        model.addAttribute("post", post);

        // REPLIES 테이블에서 해당 포스트에 달린 댓글 개수를 탐색.
        // List<Reply> replyList = replyService.read(post);
        long count = replyService.countByPost(post);
        // model.addAttribute("replyCount", replyList.size());
        model.addAttribute("replyCount", count);

        // 컨트롤러의 메서드의 리턴값이 없는 경우(void인 경우),
        // 뷰의 이름은 요청 주소와 같다!
        // details -> details.html, modify -> modify.html
    }

    // 삭제
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/delete")
    public String delete(Long id) {
        log.info("delete(id = {})", id);

        // PostService를 이용해서 DB 테이블에서 포스트를 삭제하는 서비스 호출:
        postService.delete(id);

        return "redirect:/post";
    }

    // 수정
    // form의 name 속성과 DTO 필드 값이 같을 경우 찾을 수 있음.
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/update")
    public String update(PostUpdateDto dto) {
        log.info("update(dto={})", dto);

        // 포스트 업데이트 서비스 호출:
        /*
         * Post entity = postRepository.findById(dto.getId()).orElseThrow();
         * entity.update(dto);
         */

        postService.update(dto);

        // 쿼리 스트링에서는 중간에 공백이 있으면 안됨.
        return "redirect:/post/details?id=" + dto.getId();
    }

    @GetMapping("/search")
    public String search(PostSearchDto dto, Model model) {
        log.info("search(dto = {})", dto);

        // postService의 검색 기능 호출:
        List<Post> list = postService.search(dto);

        // 검색 결과를 Model에 저장해서 뷰에 전달:
        model.addAttribute("posts", list);

        return "/post/read";
    }

}
