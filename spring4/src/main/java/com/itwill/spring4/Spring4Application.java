package com.itwill.spring4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing 
// JPA Auditing 기능 활성화. -> 반드시 main class에 작성하기
// -> BaseTimeEntity와 연결
@SpringBootApplication
public class Spring4Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring4Application.class, args);
	}

}
