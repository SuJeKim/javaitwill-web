package com.itwill.spring2.stream;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

// 스프링 컨텍스(application-context.xml 또는 servlet-context.xml)를 사용하지 않는
// 단위 테스트에서는 @ExtendWith, @Contextconfiguration 애너테이션을 사용할 필요가 없음.
public class StreamTest {

    @Test
    public void test() {
       List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
       System.out.println(numbers);
    }
    
    
}
