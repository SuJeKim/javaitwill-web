package com.itwill.spring2.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = { "file:src/main/webapp/WEB-INF/application-context.xml" } // 해당 파일에서 설정한 것들을 unitTest가 알아야하기에 파일위치 설정.
)
public class HikariCPTest {
 
    /*
     * 의존성 주입(dependency injection), 제어의 역전(IoC: Inversion of Control):
     * 전통적인 자바 개발에서는 객체를 사용하는 곳에서 생성자를 호출하고, 메서드를 이용.
     * 스프링에서는 스프링 컨테이너(객체를 가지고 잇음)가 필요한 객체들을 미리 메모리에 생성해 두고,
     * 필요한 곳에서 변수 선언과 애너테이션(선언)을 사용하면, 
     * 스프링 컨테이너가 필요한 곳에 객체를 주입하는 개발 방식.
     * 
     * 제어의 역전 ->
     * 객체를 생성하는 제어권을 가지고 있는 곳은 수동적으로 변했고, 스프링 프레임워크는 객체의 제어권과 주도권을 가짐.
     */

    @Autowired // 스프링 컨테이너에서 (생성하고) 관리하는 bean을 변수에 자동 할당.
    @Qualifier("hikariConfig") // 해당 id를 가지고 있는 것으로 넣어라
    private HikariConfig config; 
    // 기본적으로 생성자를 호출하면 null
    // springFrameWork(application-context.xml): 의존성 주입을 제공하는 frameWork.
    // 일반적인 객체 지향 프로그램에서는 객체를 사용하는 곳에서 객체를 담당함. -> 생성자를 호출함.
    // 객체들의 생성을 객체를 사용하고자 하는 클래스가 아니라 springFrameWork(객체들을 관리하는 클래스)가 관리를 함. + 해당 객체를 여러번 생성하지 않음. 딱 하나만 생성함.
    //  -> xml 설정을 하고 잇어야 함.(application-context.xml에 HikariConfig를 설정함)
    //  -> 따로 싱글톤을 만들 필요가 없다. 
    // @Autowired('의존성 주입'-자동으로 ):
    // 이 변수에다가 생성된 객체를 자동으로 넣어주세요. + 필요한 곳에 springFrameWork가 생성을 해 줌
    //  -> 그래서 해당 변수의 값이 null이 아닌 값으로 나옴.
    //  -> 소스코드의 변경이 최소화가 됨. 변경을 하려면 xml에서 변경하면 자동으로 변경.
    
    /*
     * HikariConfig : super class
     * |__ HikariDataSource : sub class
     * 다형성(polymorphism) 때문에 HikariConfig 타입에는 
     * HikariConfig 객체와 HikariDataSource 객체를 모두 주입할 수 있다.
     * application-context.xml에서 설정한 id 값을 이용해 특정 bean을 주입받고자 할 때에는
     * @Qualifier("id") 애너테이션을 사용하면 됨.
     */
    
    @Autowired
    private HikariDataSource ds; 
    
    // @Qualifier가 없을 경우 +
    // 2개가 동시에 있을 경우 오류가 나는 이유:
    // -> 다형성 때문에 HikariConfig <- HikariDataSource(상속 관계) => config에 넣어야 하는 것이 무엇인지 헷갈리게 됨.
    // HikariDataSource는 HikariConfig이다.  HikariDataSource에서 HikariConfig를 사용할 수 있음
    
    @Autowired
    private SqlSessionFactoryBean sessionFactory;
    
    @Test
    public void testSqlSession() {
        Assertions.assertNotNull(sessionFactory);
        log.info("session = {}", sessionFactory);
    }
    
    @Test
    public void testDataSource() throws SQLException {
        Assertions.assertNotNull(config);
        log.info("config = {}", config);
    
        Connection conn = ds.getConnection(); // Data Source에서 Connection을 빌려옴.
        Assertions.assertNotNull(ds);
        log.info("ds = {}", ds);
        
        conn.close(); // 사용했던 Connection을 Data Source에 반환.
        log.info("conn close 성공");
    }
    
}
