package com.itwill.spring4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring4.dto.member.MemberSignUpDto;
import com.itwill.spring4.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemeberController {
    
    private final MemberService memberService;

    @GetMapping("/signup") // 주소는 가능하면 전부 다 소문자로 사용하기
    public void signUp() {
        log.info("signUp() GET");
    }
    
    @PostMapping("/signup")
    public String signUp(MemberSignUpDto dto) {
        log.info("signUp(dto ={}) POST", dto);
        
        // 회원 가입 서비스 호출
        Long id = memberService.registerMember(dto);
        log.info("회원가입 id ={}", id);
        
        // 회원 가입 이후에 로그인 화면으로 이동(redirect):
        return "redirect:/login";
    }
}
