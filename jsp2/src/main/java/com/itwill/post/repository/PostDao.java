package com.itwill.post.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.datasource.HikariDataSourceUtill;
import com.itwill.post.model.Post;
import com.zaxxer.hikari.HikariDataSource;

// Repository(Persistence) Layer(저장소/영속성 계층) 
// DB DRUD(Create, Read, Update, Delete) 작업을 수행하는 계층.
public class PostDao {
    
    // Slf4j 로깅 기능 사용:
    private static final Logger log = LoggerFactory.getLogger(PostDao.class);
    
    // singleton
   private static PostDao instance = null;
   
   private HikariDataSource ds;
   
   private PostDao() {
       ds = HikariDataSourceUtill.getinstance().getDataSource();
       
   }
   
   public static PostDao getinstance() {
       if(instance == null) {
           instance = new PostDao();
       }
       return instance;
   }
   
   // POSTS 테이블에서 전체 레코드를 id 내림차순으로 정렬(최근 작성 포스트 먼저)해서 검색.
   private static final String SQL_SELECT_ALL = 
           "select * from POSTS order by ID desc";
   
   public List<Post> select() {
       
       List<Post> list = new ArrayList<>();
       
       log.info(SQL_SELECT_ALL);
       
       Connection conn = null;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       try {
           conn = ds.getConnection(); // Pool에서 Connection 객체를 빌려옴.
           stmt = conn.prepareStatement(SQL_SELECT_ALL);
           rs = stmt.executeQuery();
           while(rs.next()) {
               // 테이블의 컬럼 내용을 Post 타입 객체로 변환하고 list에 추가:
               Post post = recordToPost(rs);
               list.add(post);
           }
           log.info("list size / num of rows = {}", list.size());
           
           
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
               rs.close();
               stmt.close();
               conn.close(); // 물리적인 접속 해제가 아니라, Pool에 반환!
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       
       
       
       return list;
   }

   /**
    * 테이블의 컬럼 내용을 Post 타입 객체로 변환
    * @param rs
    * @return
 * @throws SQLException 
    */
   private Post recordToPost(ResultSet rs) throws SQLException {
       
       long id = rs.getLong("ID");
       String title = rs.getString("TITLE");
       String content = rs.getString("CONTENT");
       String author = rs.getString("AUTHOR");
       LocalDateTime created = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
       LocalDateTime modified = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();
       
       Post post = new Post(id, title, content, author, created, modified);
       
       return post;
   }
   
   

} // class end
