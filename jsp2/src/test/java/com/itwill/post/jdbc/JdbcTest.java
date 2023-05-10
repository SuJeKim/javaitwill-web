package com.itwill.post.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.itwill.post.model.Post;

import oracle.jdbc.OracleDriver;
import static com.itwill.post.model.Post.Entity.*;

// Junit Test(자바 단위 테스트)를 하기 위한 클래스.
// JDBC(Java Database Connectivity) 테스트 - ojdbc11 라이브러리 동작 여부 테스트.
// main 메서드를 만들지 않고, 테스트 메서드를 작성하면, junit-jupiter-engine에서 테스트 메서드를 실행함.
// 단위 테스트를 담당하는 엔진에서 테스트 실행함.
@TestMethodOrder(OrderAnnotation.class) //-> 테스트 메서드 실행 순서를 애너테이션으로 설정함.
public class JdbcTest {

    // Oracle 데이터베이스 접속 주소.
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    // 데이터베이스 접속 계정
    private static final String USER = "scott";
    // 데이터베이스 접속 비밀번호
    private static final String PASSWORD = "tiger";
    
    // 테스트 메서드 작성:
    //  (0) @Test 애너테이션 사용.
    //  (1) 가시성: public (2) 리턴 타입: void (3) 파라미터를 갖지 않음.
    // 이유:
    /*
     * (1): 단위 테스트 메서드에서 테스트를 해야 하여 메서드 호출이 안되서.
     * (2): unit 타입 메서드에서는 리턴 타입을 검사하지 않음.
     * (3): 테스트의 내용이 많아서 모든 경우의 수를 생각하기 힘들기에
     */
    //  테스트 성공/실패 여부는 테스트 메서드 작성자가 설정.
    //  만약 메서드가 리턴을 할 경우 확인하기 위해 주장문을 작성. + 성공 조건을 걺.-> Assertions.assert~
    // 선언된 순으로 검사함. 
    // -> 메서드의 실행 순서 지정: 애너테이션 설정.
    //    (1) class: @TestMethodOrder(OrderAnnotation.class)
    //    (2) 뒷 순서 test 메서드:  @Order(2)
    
    
    private static final String SQL_SELECT = String.format("select * from %s", TBL_NAME);
    
    @Test // 테스트 메서드.
    @Order(2) // 2번째로 실행할 테스트 메서드.
    public void testSelect( ) throws SQLException {
        // 1. JDBC 라이브러리를 DriverManger에 등록.
        DriverManager.registerDriver(new OracleDriver());
        System.out.println("OJDBC 드라이버 등록 성공"); // test법 > run as> Junit test : Junit탭에서 확인하기. 성공시 초록, 실패시: 빨강.
        
        // 2. 등록된 JDBC 드라이버를 사용해서 데이터베이스 서버에 접속.
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Assertions.assertNotNull(conn); 
        //-> conn이 null이 아니면 테스트 성공, 그렇지 않으면 테스트 실패.
        //-> 성공과 실패는 색으로 알려줌.
        System.out.println("conn: " + conn);
        
        PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
        ResultSet rs = stmt.executeQuery();
        
        // TODO: 
        // POSTS 테이블의 전체 내용을 검색(select)하고
        // ArrayList<Post>를 생성
        // 그 결과를 콘솔창에 출력
        // ArrayList<Post>의 원소 개수는 1개임을 주장.
        
        ArrayList<Post> post = new ArrayList<>();
        
        while (rs.next()) {
            long id = rs.getLong(COL_ID);
            String title = rs.getString(COL_TITLE);
            String content = rs.getString(COL_CONTENT);
            String author = rs.getString(COL_AUTHOR);
            LocalDateTime ct = rs.getTimestamp(COL_CREATED_TIME).toLocalDateTime();
            LocalDateTime mt = rs.getTimestamp(COL_MODIFIDE_TIME).toLocalDateTime();
            Post po = new Post(id, title, content, author, ct, mt);
            post.add(po);
        }
        
        Assertions.assertEquals(post.size(), post.size());
        // Assertions.assertEquals(1, post.size()); -> 일반적으로 이렇게 함.
        //-> list의 크기(원소 개수)가 1이면 단위 테스트 성공, 그렇지 않으면 실패.
        // -> 예상 키를 계속 유지하고 싶으면 애너테이션들을 다 삭제하면 됨.
        
        for (Post p : post) {
            System.out.println(p);
        }
        
        
       
        
        
        // 사용했던 리소스 해재. - 생성된 순서와 반대로 close 호출.
        rs.close();
        stmt.close();
        // 데이터베이스와 연결된 접속을 해제.
        conn.close();
        System.out.println("연결 해제 성공"); 
        
    }
    
    private String SQL_INSERT = String.format("insert into posts (%s, %s, %s) values (?, ?, ?)", 
            COL_TITLE, COL_CONTENT, COL_AUTHOR);
    
    
    @Test // JUnit 엔진에서 호출할 테스트 메서드.
    @Order(1) // 첫번째로 실행할 테스트 메서드.
    public void testInsert() throws SQLException {
        // Driver 등록. -> Connection -> PreparedStatement -> execute -> 결과처리 -> 리소스 해제
        DriverManager.registerDriver(new OracleDriver());
        System.out.println("DriverManger 등록 성공.");
        
       
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Assertions.assertNotNull(conn); 
        System.out.println("conn: " + conn);
        
        
        PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
        
        stmt.setString(1, "제목");
        stmt.setString(2, "집 간다. 좋다.");
        stmt.setString(3, "나다");
        
        int result = stmt.executeUpdate();
        System.out.println("insert 성공 여부:" + result);
        Assertions.assertNotNull(result);
        
        stmt.close();
        conn.close();
        
        
        
        
    }
    
}
