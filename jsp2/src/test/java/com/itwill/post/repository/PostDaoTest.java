package com.itwill.post.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.Post;

public class PostDaoTest {
    
    private static final Logger log = LoggerFactory.getLogger(PostDaoTest.class);
    
    @Test
    public void testSelect() {
        PostDao dao = PostDao.getinstance();
        List<Post> list = dao.select();
        Assertions.assertNotEquals(0, list.size()); // assertEquals와 반대.
        
        for(Post p : list) {
            log.info(p.toString()); // info메서드는 Object를 argument로 받지 않음.
        }
        
    }

}
