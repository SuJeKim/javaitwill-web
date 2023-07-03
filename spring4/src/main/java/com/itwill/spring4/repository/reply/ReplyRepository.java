package com.itwill.spring4.repository.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.spring4.repository.post.Post;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
    // Post id로 검색하기:
    // 찾았지만, 중간에 Reply의 Post값이 채워지지 않아 실패.
    // List<Reply> findByPostId(Long postId);
    
    
    // Post (id)로 검색하기:
    List<Reply> findByPostOrderByIdDesc(Post post);
    
    // Post에 달린 댓글 개수:
    Long countByPost(Post post);
    
}
