package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.domain.Reply;

public interface ReplyRepository {

    List<Reply> selectByPostId(long postId); // parameter가 reply-mapper.xml의 #{post_id}(== ? )를 채움.
    
    int insert(Reply reply);
    
    int update(Reply reply);
    
    int delete(long id);
    
    // 포스트에 달린 갯수를 리턴
    long selectReplyCountWithPostId(long postId);
}
