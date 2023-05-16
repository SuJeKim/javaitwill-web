package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


/*
 * POJO(Plain Old Java Object): 평범한 자바 객체
 * -특정 클래스를 상속해야만 하거나, 상속 후에 메서드들을 override해야만 하는 클래스가 아님.
 * 
 * 스프링 프레임워크는 POJO로 작성된 클래스를 controller로 사용할 수 있음. -> servlet을 상속받지 않아도 controller로 사용할 수 있음.
 *  + 다른 과정은 dispatcherServlet이 다 함.
 */

@Slf4j // Logger 객체(log)를 생성.
@Controller 
// Spring MVC 컴포넌트 애너테이션(@Component, @Controller, @Service, @Repository, ...) 중 하나. 
// Controller 컴포넌트라는 것을 dispatcherServlet에게 알려주는 역할
public class ExampleController {

    
    // 해당 애너테이션은 Handler Mapping.
    @GetMapping("/") // GET 방식의 요청 주소가 "/"(Context root)인 요청을 처리하는 메서드.
    // -> 추가 설명.
    // 해당(요청) 주소를 처리하는 메서드를 호출 즉, 요청 매핑/ 각 메서드마다 작성, 
    // 애너테이션을 사용함으로써 Dispatcher Servlet에서 찾을 수 있게 됨. 
    public String home(Model model) {
        log.info("home()"); // log가 오류가 나면 이클립스에서 lombok 라이브러리를 가지고 있어야 됨. + Help > about:에서 lombok이 보이면 해결됨(없으면 종료).
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now);
        
        return "index"; // 매핑되어 있는 controller method가 리턴하는 것: 뷰의 이름.
    }
    
    @GetMapping("/ex1") // context-root를 제외한 주소 작성.
    public void example1() {
        log.info("example() 호출");
        // 컨트롤러 메서드가 void인 경우(뷰의 이름을 리턴하지 않는 경우)
        // 요청 주소의 이름이 뷰의 이름이 됨.
    }
    // controller 메서드가 아무것도 리턴하지 않으면 파일 이름을 요청주소랑 똑같은 것을 찾음.(뷰를 찾음)  
    /* -> Servlet-context.xml에서 다음과 같이 설정을 했기에 가능함.
     * <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" /> <!-- 폴더 생성 + 리턴을 할 경우 리턴값이 value="/" 뒤에 붙음. 그래서 파일이름만 붙이면 됨.-->
        <property name="suffix" value=".jsp" /> <!-- 해당 폴더의 파일의 확장자가 .jsp -->
     </bean>   
     */
}
