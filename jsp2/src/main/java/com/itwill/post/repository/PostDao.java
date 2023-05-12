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

   // 새 포스트 작성.
   private static final String SQL_INSERT =
           "insert into POSTS (TITLE, CONTENT, AUTHOR) values (?, ?, ?)";
   
   public int insert(Post post) {
       log.info("insert({})", post);
       log.info(SQL_INSERT);
       
       int result = 0; // executeUpdate() 결과(insert 결과)를 저장할 변수
       
       Connection conn = null;
       PreparedStatement stmt = null;
       
       try {
        conn = ds.getConnection();
        stmt = conn.prepareStatement(SQL_INSERT);
        
        stmt.setString(1, post.getTitle());
        stmt.setString(2, post.getContent());
        stmt.setString(3, post.getAuthor());
        
        result = stmt.executeUpdate();
        
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
       return result;
   }
   
   // 포스트 번호로 검색.
   private static final String SQL_SELECT_BY_ID =
           "select * from POSTS where ID = ?";

   public Post select(long id) {
       log.info("select(id={})", id);
       log.info(SQL_SELECT_BY_ID);
       
       //List<Post> list = new ArrayList<>();
       
       Post post = null;
       Connection conn = null;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       try {
           conn = ds.getConnection();
           stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
           
           stmt.setLong(1, id);
           log.info(SQL_SELECT_BY_ID);
           
           rs = stmt.executeQuery();
           
           if(rs.next()) {
               post = recordToPost(rs);
               //list.add(post);
           }
           //log.info("list size / num of rows = {}", list.size());
           
           
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
               rs.close();
               stmt.close();
               conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }

       }
       
       
       return post;
   }
   
   // 포스트 아이디(PK)로 삭제하기:
   private static final String SQL_DELETE_BY_ID =
           "delete from POSTS where ID = ?";
   
   public int delete(Long id) {
       log.info("delete({})", id);
       log.info(SQL_DELETE_BY_ID);
       
       int result = 0; // sql 실행 결과를 저장할 변수.
       Connection conn = null;
       PreparedStatement stmt = null;
       try {
        conn = ds.getConnection();
        
        stmt = conn.prepareStatement(SQL_DELETE_BY_ID);
        
        stmt.setLong(1, id);
        
        result = stmt.executeUpdate();
        
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
       return result;
   }
   
   // 해당 아이디의 포스트의 제목과 내용, 수정 시간을 업데이트
   
   private static final String SQL_UPDATE_BY_ID =
           "update POSTS set TITLE = ?, CONTENT = ?, MODIFIED_TIME = sysdate where ID = ?";
   
   
   public int update(Post post) {
       log.info("update({})", post);
       log.info(SQL_UPDATE_BY_ID);
       
       int result = 0;
       
       Connection conn = null;
       PreparedStatement stmt = null;

       try {
           conn = ds.getConnection();
           stmt = conn.prepareStatement(SQL_UPDATE_BY_ID);
           
           stmt.setString(1, post.getTitle());
           stmt.setString(2, post.getContent());
           stmt.setLong(3, post.getId());
           
           result = stmt.executeUpdate();
           
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       }
       
       
       
       return result;
   }
   
   // 비슷한 문자열을 찾는다.
   
   private static final String SQL_SEARCH_TWICE = 
           "select * from POSTS WHERE TITLE like ? or CONTENT like ?";
   
   private static final String SQL_SEARCH_TITLE = 
           "select * from POSTS WHERE TITLE like ?";
   
   private static final String SQL_SEARCH_CONTENT = 
           "select * from POSTS WHERE CONTENT like ?";
   
   private static final String SQL_SEARCH_AUTHOR = 
           "select * from POSTS WHERE AUTHOR like ?";
   
 

   public List<Post> searchByvalues(String values, String keyword) {
       log.info("searchByvalues({}, {})", values, keyword);
       
       
       List<Post> list = new ArrayList<>();
       
       Post post = null;
       Connection conn = null;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       String title = null;
       String content = null;
       String author = null;
       
      

       try {
           
           conn = ds.getConnection();
           
           if(values.equals("t")) {
               
               log.info(SQL_SEARCH_TITLE);
               stmt = conn.prepareStatement(SQL_SEARCH_TITLE);
               stmt.setString(1, "%" + keyword + "%");
               
               rs = stmt.executeQuery();
               
               while(rs.next()) {
                   post = recordToPost(rs);
                   list.add(post);
               }
               log.info("list size / num of rows = {}", list.size());
               
           } else if (values.equals("c")) {
               
               log.info(SQL_SEARCH_CONTENT);
               
               stmt = conn.prepareStatement(SQL_SEARCH_CONTENT);
               stmt.setString(1, "%" + keyword + "%");
               
               rs = stmt.executeQuery();
               
               while(rs.next()) {
                   post = recordToPost(rs);
                   list.add(post);
               }
               log.info("list size / num of rows = {}", list.size());
               
           } else if (values.equals("tc")) {
               
               log.info(SQL_SEARCH_TWICE);
               
               stmt = conn.prepareStatement(SQL_SEARCH_TWICE);
               stmt.setString(1, "%" + keyword + "%");
               
               rs = stmt.executeQuery();
               
               while(rs.next()) {
                   post = recordToPost(rs);
                   list.add(post);
               }
               log.info("list size / num of rows = {}", list.size());
               
           } else if (values.equals("a")) {
               
               log.info(SQL_SEARCH_AUTHOR);
               
               stmt = conn.prepareStatement(SQL_SEARCH_AUTHOR );
               stmt.setString(1, "%" + keyword + "%");
               
               rs = stmt.executeQuery();
               
               while(rs.next()) {
                   post = recordToPost(rs);
                   list.add(post);
               }
               log.info("list size / num of rows = {}", list.size());
               
           }
               
           
           
           
          
           
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       }
       
       return list;
   }
  
   
   

} // class end
