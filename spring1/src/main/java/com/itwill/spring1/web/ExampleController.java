package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Logger 객체(log)를 생성.
@Controller // Spring MVC 컴포넌트 애너테이션 중 하나. Controller 컴포넌트라는 것을 dispatcherServlet에게 알려줌.
public class ExampleController {

    @GetMapping("/") // 해당 주소에 있는 메서드를 호출, 각 메서드마다 작성, 애너테이션을 사용함으로써 Dispatcher Servlet에서 찾을 수 있게 됨. 
    public String home(Model model) {
        log.info("home()"); // log가 오류가 나면 이클립스에서 lombok 라이브러리를 가지고 있어야 됨. + Help > about:에서 lombok이 보이면 해결됨(없으면 종료).
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now);
        
        return "index";
    }
    
}
