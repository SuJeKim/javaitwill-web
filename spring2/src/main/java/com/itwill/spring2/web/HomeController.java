package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Logger log 생성. -> lombok에 저장.
@Controller // DispatcherServlet(Spring Component)에게 등록/말함.
public class HomeController {

    @GetMapping("/") // 파일 이름으로 사용할 수 없기에 리턴 값 필요
    public String home() {
        log.info("home()");
        
        return "index";
    }
    
    
}
