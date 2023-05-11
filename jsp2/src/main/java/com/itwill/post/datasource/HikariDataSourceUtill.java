package com.itwill.post.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceUtill {
    // singleton 디자인 패턴:
    // Connection Pool은 하나만 존재하게
    private static HikariDataSourceUtill instance = null;
    
    private HikariDataSource ds;
    
    private HikariDataSourceUtill() {
        // HikariCP를 사용하기 위한 환경 설정 객체:
        HikariConfig config = new HikariConfig();
        
        // CP(Data Source)을 생성 하기 위한 설정들:
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("scott");
        config.setPassword("tiger");
        
        // CP(Data Source) 객체 생성:
        ds = new HikariDataSource(config);
    }
    
    public static HikariDataSourceUtill getinstance() {
        if ( instance == null) {
            instance = new HikariDataSourceUtill();
        }
        
        return instance;
    }
    
    // 항상 null이 아닌 값을 리턴할 수 없음.
    public HikariDataSource getDataSource() {
        return ds;
    }
    
    
    
    
    
    
    
    
} // class end
