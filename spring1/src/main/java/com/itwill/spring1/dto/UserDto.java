package com.itwill.spring1.dto;

import lombok.Data;

// DTO(Data Transfer Object): 계층간의 데이터 전달을 위한 객체.
// DispatcherServlet <=== (Data) ===> Controller
// Controller <=== (Data) ===> Service

// VO(Value Object): 값을 저장하기 위한 객체. 모델
// 데이터베이스의 테이블 구조를 자바 클래스로 표현한 객체.

// -> 설계하고픈 대로 2개다 써도 되고 1만 써도 되고....

@Data // Lombok.Data => getter/setter/toString/Constructor/...를 다 만들어줌. --> (show view) Outline에서 확인할 수 있음
public class UserDto {
    // 폼에서 전달한 요청 파라미터 값들을 저장하기 위한 클래스.
    
    // 변수 이름 잘 쓰기. 테이블의 변수에 맞게/ request Parameter에 맞게.
    private String username;
    private int age;
    // 요청 파라메타 이름과 setter메서드를 비교함. -> set + 요청 파라메타 -> 호출함.
    
}
