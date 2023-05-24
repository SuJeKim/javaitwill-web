package com.itwill.spring2.stream;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

// 스프링 컨텍스(application-context.xml 또는 servlet-context.xml)를 사용하지 않는
// 단위 테스트에서는 @ExtendWith, @Contextconfiguration 애너테이션을 사용할 필요가 없음.
public class StreamTest {

    @Test
    public void test() {
       List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
       System.out.println(numbers);
       
       // numbers에서 홀수들만 필터링한 결과:
       List<Integer> odds = numbers.stream()
               .filter((x) -> {
                   return x % 2 == 1; // body + 1개 이상의 문장이 잇을 경우에는 {}, ; 을 사용. 아닐 경우 둘 다 생략.
                })
               .toList();    
       System.out.println(odds);        
       
       // numbers의 원소들의 제곱.
       List<Integer> squares = numbers.stream().map((x) -> x*x).toList();
       System.out.println(squares);
       
       // numbers의 원소들 중 홀수들의 제곱
       List<Integer> oddSquares = odds.stream().map((x) -> x*x).toList();
       System.out.println(oddSquares);
       
       List<Integer> oddsquares2 = numbers.stream().filter((x) -> x % 2 == 1).map((x) -> x * x).toList();
    
       //
       List<String > languages = Arrays.asList("Java", "SQL", "JavaScript");
       System.out.println(languages);
       
       // languages가 가지고 있는 문자열들의 길이를 원소로 갖는 리스트.
       List<Integer> lengths = languages.stream().map((x) -> x.length()).toList();
       System.out.println(lengths);
       
       List<Integer> lengths1 = languages.stream().map(String::length).toList();
       System.out.println(lengths1);
       
       List<LocalDateTime> times = Arrays.asList(
               LocalDateTime.of(2023, 5, 23, 11, 30, 0),
               LocalDateTime.of(2023, 5, 24, 12, 30, 0),
               LocalDateTime.of(2023, 5, 25, 18, 00, 0),
               LocalDateTime.of(2023, 5, 26, 24, 24, 0)
       );
       System.out.println(times);
       
       // LocalDateTime 타입을 TimeStamp 타입으로 변환
       // argument가 그대로 들어감.
       List<Timestamp> timestamps = times.stream()
               .map(Timestamp::valueOf) // (x) -> {return Timestamp.valueOf(x);}
               .toList();
       System.out.println(timestamps);
    }
}
