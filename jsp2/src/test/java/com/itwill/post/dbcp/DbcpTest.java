package com.itwill.post.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// DBCP(Database Connection Pool) HikariCP 라이브러리 단위 테스트
public class DbcpTest {
    // Log4j2 기능을 구현한 Slf4j 라이브러리의 로깅 기능 사용해서 로그 출력:
    // 클래스 이름을 주면서 log객체를 만들었기에 그 다음부터는 자동으로 클래스 이름들이 log 출력이 됨 => %C
    private final Logger log = LoggerFactory.getLogger(DbcpTest.class); // argument: 우리가 만드는 클래스 이름 작성
    
    @Test // JUnit 단위 테스트 엔진이 실행할 메서드.
    public void testHikariCP() throws SQLException {
        // HikariCP 환경 설정을 위한 객체 생성:
        HikariConfig config = new HikariConfig();
        
        // HikariCP 환경 설정:
        config.setDriverClassName("oracle.jdbc.OracleDriver"); // JDBC 드라이버(라이브러리) 이름
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe"); // DB 접속 URL
        config.setUsername("scott"); // DB 접속 계정.
        config.setPassword("tiger"); // DB 접속 비밀번호.
        
        // 환경 설정을 갖는 Connection Pool(Data Source) 객체 생성:
        HikariDataSource ds = new HikariDataSource(config);
        
        // ds가 null이 아니면 단위 테스트 성공, 그렇지 않으면 실패.
        Assertions.assertNotNull(ds);
        
        log.info("ds = {}", ds); // string.format/printf와 비슷함. => {}: 정수/객체등 타입 고려 안함.
        // xml에서 설정한 info 이상이 아닌 로그 레벨을 사용할 경우 나오지 않음.
        // -> 개발 단계에서 각 용도에 맞게끔 메서드를 부름.
        // 내가 출력한 것 외에 위에 나오는 3개의 문장: slf4j 라이브러리에서 제공해주는 것 보여줌.
        
        // Connection Pool(Data Source)에서 Connection 객체를 빌려옴.
        Connection conn = ds.getConnection(); // pool에서 가지고 온 객체: T4CConnection@42e3ede4를 가지고 있는 HikariProxyConnection
        
        // conn이 null이 아니면 단위 테스트 성공, 그렇지 않으면 실패.
        Assertions.assertNotNull(conn);
        log.info("conn = {}" , conn);
        
        // 사용했던 Connection 객체를 Pool에 반환.
        conn.close(); // 데이터베이스 서버(oracle.jdbc)와의 접속을 물리적으로 끊는 게 아님. 
        log.info("conn 반환 성공.");
        
    }
    
}
