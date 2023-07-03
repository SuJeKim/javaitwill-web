package com.itwill.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
// 스프링 컨테이너(프레임 워크)에서 빈(bean-객체)으로 생성, 관리 - 필요한 곳에 의존성 주입.
// 환경설정.
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // Spring Security 5버전부터 비밀번호는 반드시 암호화를 해야 함.
    // 비밀번호를 암호화하지 않으면 HTTP 403(access denied, 접근 거부) 또는 
    // HTTP 500(internal server error, 내부 서버 오류)가 발생함.
    //-> (웹 서비스에서 사용하는) 비밀번호 인코더(Password encoder) 객체를 bean으로 생성해야 함.
    
    /**
     * BCryptPasswordEncoder: 
     * 비밀번호를 다른 문자열로 리턴.
     * 암호화가 필요한 곳에 사용
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 인터페이스는 기본생성자로 안되니 'The preferred implementation is BCryptPasswordEncoder.' 를 참고하여 함.
    }

    // 로그인할 때 사용할 임시 사용자(메모리에 임시 저장) 생성
    // 다음을 생성시 임시로 알려준 spring 비번을 알려주지 않음
    // spring security filter/dipatcher Service는 UserDetailsService을 찾고 사용자 상세정보를 만들어줌
    // 그래서 해당 클래스를 상속받아야 그대로 동작이 가능함.
    // UserDetailsService: 로그인 필터가 사용자 정보를 가져오고자 하는 클래스. 
    // -> 클래스 에서 UserDetailsService를 구현해야 로그인 필터가 사용자 정보를 가지고 올 수 있음.
    // -> 또한, UserDetailsService에는 userDetails 정보가 꼭!! 필요함.
    
    /* @Bean
    public UserDetailsService  inMemoryUserDetailsServic() {
        // repository가 됨. 임시로 생성한 일종의 Entity가 됨
        // 사용자 상세 정보.
        UserDetails user1 = User
                    .withUsername("user1") // 로그인할 떄 사용할 사용자 이름
                    .password(passwordEncoder().encode("1111")) // 로그인할 때 사용할 비밀번호
                    .roles("USER") // 사용자 권한(USER, ADMIN,.... -> 사용자에 따라 페이지 구분 가능함.)
                    .build(); //UserDetails 객체 생성.
        
        UserDetails user2 = User
                .withUsername("user2") 
                .password(passwordEncoder().encode("2222")) 
                .roles("USER", "ADMIN") 
                .build(); 
        
        UserDetails user3 = User
                .withUsername("user3") 
                .password(passwordEncoder().encode("3333")) 
                .roles("ADMIN") 
                .build(); 
        
        return new InMemoryUserDetailsManager(user1, user2, user3);
    } */
    
    
    // 찐 환경설정.
    // Security Filter 설정 Bean 
    // -> Security Filter: 로그인관련된 인증 필터
    // (예) 로그인/로그아웃 설정, 로그인 페이지 설정, 로그아웃 이후 이동할 페이지, 페이지 접근 권한 - 로그인 해야만 접근 가능한 페이지, 로그인 없이 접근 가능한 페이지.
    // 다음 SecurityFilterChain설정을 했기에 로그인없이 들어가짐.(~86까지 만듦) -> 페이지 권한 설정을 안 했기에, 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // CSRF(Cross Site Request Forgery) 기능 활성화하면,
        //-> 보안은 좋음 단, Ajax POST/PUT/DELETE 요청에서 CSRF 토큰을 서버로 전송하지 않으면 403(접근 거부) 에러가 발생
        //-> CSRF 기능 비활성화.
        http.csrf((csrf)-> csrf.disable());
        
        // 로그인 페이지 설정 - withDefaults: 스피링에서 제공하는 기본 로그인 페이지를 사용.
        // -> 개인 설정 로그인 페이지 사용시 Customizer.다음을 설정하면 됨
        http.formLogin(Customizer.withDefaults());
        
        // 로그아웃 이후 이동할 페이지
        //-> 메인 페이지로 이동.
        http.logout((logout) -> logout.logoutSuccessUrl("/") );
        
        
       /* // 페이지 접근 권한 설정 -> 순서 중요
        http.authorizeHttpRequests((authRequest) -> 
                            authRequest // 접근 권한을 설정할 수 있는 객체
                            // 권한이 필요한 페이지들을 설정
                            .requestMatchers("/post/create", "/post/details", "/post/modify", "/post/update", "/post/delete"
                                    ,"/api/reply/**") // 다음 페이지는 + 쿼리 스프링 따로 표현 안 해도 됨.
//                            .authenticated() -> 권한 여부에 상관없이 아이디/ 비번이 일치하면 
                            .hasRole("USER") // 위에서 설정한 페이지들이 USER 권한을 요구함을 설정.
                            .anyRequest() // .requestMatchers("/**") -> 위 페이지들 이외의 모든 페이지
                                          // (/post/details) -> **: 하위 주소(/details)가 몇 단계가 와도 상관없음
                            .permitAll()); // 권한없이 접근 허용
        */
        
        // 단점: 새로운 요청 경로, 컨트롤러를 작성할 때마다 Config 자바 코드를 수정해야 함.
        // -> 컨트롤러 메서드를 작성할 때 에너테이션을 사용해서 접근 권한을 설정할 수 있도 있음.
        // (1) SecurityConfig 클래스에서 @EnableMethodSecurity 에너테이션 설정 -> controller 메서드에서 security 설정. 
        // (2) 각각의 컨트롤러 메서드에서 @PreAuthorize 또는 @PostAuthorize(메서드 끝난 다음에 인증 -> 드묾) 애너테이션을 사용.
        
        
        return http.build();
    }

}
