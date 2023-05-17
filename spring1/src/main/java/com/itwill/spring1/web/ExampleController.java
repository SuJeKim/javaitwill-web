package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.spring1.dto.UserDto;

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
// 여기서는 작성만 함. 호출은 dispatcherServlet이.
public class ExampleController {

    
    // 해당 애너테이션은 Handler Mapping.
    @GetMapping("/") // GET 방식의 요청 주소(요청 parameter)가 "/"(Context root)인 요청을 처리하는 메서드.
    // -> 추가 설명.
    // 해당(요청) 주소를 처리하는 메서드를 호출 즉, 요청 매핑/ 각 메서드마다 작성, 
    // 애너테이션을 사용함으로써 Dispatcher Servlet에서 찾을 수 있게 됨. 
    
    
    // forward나 redirect가 아닐 경우에 model에 저장을 해서 전달을 하면 됨.
    // controller에서 뷰로 데이터를 전달할 경우
    
    public String home(Model model) { // model에 있는 값들은 해당 request(요청 주소)에서만 존재함.
        log.info("home()"); // log가 오류가 나면 이클립스에서 lombok 라이브러리를 가지고 있어야 됨. + Help > about:에서 lombok이 보이면 해결됨(없으면 종료).
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now); 
        // 뷰에 전달할 데이터를 세팅: 무조건 model 객체의 주소값을 가지고 와서 설정하고 넣으면 됨. argument model은 Dispatcher Servlet이 model객체를 생성하고 argument로 전달.
        // 데이터를 전달하는 주체는 Dispatcher Servlet임.(jsp로 forward를 할 떄, model객체도 같이 넘김. -> jsp는 그것을 바탕으로 동적으로 HTML을 만들어서 DispatcherServlet에게 리턴)
        
        return "index"; // 매핑되어 있는 controller method가 리턴하는 것: 뷰의 이름(/WEB-INF/views/index.jsp)
        // 애는 DispatcherServlet에게 다음을 넘겨줘. DispatcherServlet이 다음 뷰의 이름을 확인을 하고 jsp 호출 후 forward방식으로 응답을 해.
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
    
    @GetMapping("/ex2")
    public void getParamEx(String username, int age) {
        log.info("getParamEx(username={}, age={})", username, age);
    }
    // req.getParameter 리턴 값은 String임. -> 변환은 DispatcherServlet이 담당함. 호출하는 담당자가 DispatcherServlet인데 client한테 받은 request값을 분석해서 해당 parameter를 전달해줌. 
    // argument 조건: request parameter의 이름과 같게 작성해야 함.
    // 만약, 다르게 입력을 했을 경우
    // -> String의 경우  request parameter을 못 찾으면 null.
    // -> int의 경우 request parameter을 못 찾으면 req.getParameter 리턴 값은 String으로 null값이 리턴이 되는데 Integer.parserInt에서  execption됨.
    // -> 서버와 브라우저에서 다 에러 뜸
    // -> 해결 방안: 아래 참고.
    
   @PostMapping("/ex3")
   public String getParamEx2(
           @RequestParam(name = "username") String name, 
           // 요청 파라메타 이름과 변수이름이 동일하지 않을 경우 -> requestParam이 name을 찾을 수가 없어 null.
           // @RequestParam(name = "username") ==> 해당 애너테이션을 보고 requestParamer가 username을 찾고 값을 name에 넣어줌.
           @RequestParam(defaultValue = "0") int age
           // RequestParam가 비었을 경우(비여있는 문자열, null) 에러 발생함.
           // ==> @RequestParam(defaultValue = "0"): RequestParam는 항상 String이어서 기본값 설정은 항상 문자열로.
           
           // ==> @RequestParam 사용하는 이유:
           // DispatcherServlet은 RequestParam이 똑같은 이름을 찾기에 RequestParam과 Parameter의 변수가 동일한 것을 알려줄 때
           // RequestParam이 비어있는 문자열이나 null인 값을 서버쪽에서 exception이 발생하지 않도록 기본값을 설정함.
   ) {
       log.info("getParamEX2(name={}, age={})", name, age);
       
       return "ex2"; // 결과 처리 페이지를 ex2.jsp로 감.
   }
   
   @GetMapping("/ex4")
   public String getParamEx3(UserDto user) {
       log.info("getParamEx3(user = {})", user);
       // DispatcherServlet은 컨트롤러의 메서드를 호출하기 위해서,
       // 1. 요청 파라미터들을 분석(request.getParameter()).
       // 2. UserDto의 기본 생성자를 호출해서 객체를 생성.
       // 3. 요청 파라미터들의 이름으로 UserDto의 setter 메서드를 찾아서 호출.
       // 4. UserDto 객체를 컨트롤러 메서드를 호출할 때 argument로 전달.
       
       return "ex2"; 
   }
   
   @GetMapping("/sample")
   public void sample() {
       log.info("sample()");
   }
   
   @GetMapping("/forward")
   public String forwardTest() {
       log.info("forwardTest()");
       
       // Controller 메서드에서 다른 페이지(다른 요청 주소, jsp가 아님)로 forward하는 방법:
       // "forward:"으로 시작하는 문자열을 리턴하면,
       // DispatcherServlet은 리턴값이 뷰의 이름이 아니라 포워드 이동할 페이지 주소로 인식.
       // "/sample": 요청 주소. -> dispatcherServlet이 다시 호출함.
       // -> 같은 웹 서버 안에 있는 다른 페이지로 forward로 함. 이런 메서들르 호출할 수 있는 메서드를 다시 호출.
       // 처음 요청주소가 그대로 남아있는 방식: forward.
       // => 어차피 같은 페이지로 갈 것인데 이렇게 forward를 하진 않음. 잘 사용 안함.
       return "forward:/sample";
       // "forward:/sample"를 DispatcherServlet에게 전달을 하여 DispatcherServlet은 접두사를 빼고 해당 요청 주소를 reqest.getParmetar("/sample")을 찾아서
       // 해당 요청주소를 다시 controller에게 넘김. controller에서 해당 요청 주소를 찾아서 DispatcherServlet이 리턴 값을 받고 jsp를 호출 및 응답함.
       
   }
   
   // PRG 패턴: Post -> Redirect -> Get
   // 다음 패턴을 자주 사용하기에 redirect를 많이 사용함.
   // model의 경우 서로 다른 요청이 존재하기에 2개의 요청모두에 존재하지 않음. 
   // -> 첫번째 요청과 전혀 다른 2번째 요청이 있을 떄까지 존재하는 객체가 필요
   // Spring에서는 RedirectAttributes 해당.(servlet에서는 Session)
   @GetMapping("/redirect")
   public String redirectTest(RedirectAttributes attrs) {
       log.info("redirectTest()");
       
       // 컨트롤러 메서드에서 redirect되는 페이지까지 유지되는 정보를 저장할 때:
       // 한번 redirect 될 때까지만 유지. -> flashAttribute
       
       attrs.addFlashAttribute("redAttr", "테스트"); // var: String, value: Object
       
       
       // Controller 메서드에서 다른 페이지(다른 요청 주소, jsp가 아님)로 redirect하는 방법:
       // "redirect:"으로 시작하는 문자열을 리턴하면,
       // DispatcherServlet은 리턴값이 뷰의 이름이 아니라 redirect로 이동할 페이지 주소로 인식.
       return "redirect:/sample";
   }
}
