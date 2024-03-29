package com.itwill.spring4.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring4.dto.reply.ReplyCreateDto;
import com.itwill.spring4.dto.reply.ReplyUpdateDto;
import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.post.PostRepository;
import com.itwill.spring4.repository.reply.Reply;
import com.itwill.spring4.repository.reply.ReplyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public void delete(long id) {
        log.info("delete(id={})", id);

        // DB replies 테이블에서 ID(고유키)로 엔터티 삭제하기:
        replyRepository.deleteById(id);
    }

    @Transactional
    //->DB에서 검색한 엔터티를 수정하면, 트랜젝션이 끝나는 시점에 update 쿼리가 자동으로 실행됨.
    public void update(ReplyUpdateDto dto, long id) {
        log.info("update(dto ={}, id={})", dto, id);

        // 1. 댓글 아이디로 DB에서 엔터티를 검색(select):
        Reply entity = replyRepository.findById(id).orElseThrow();
        
        // 2. 검색한 엔터티의 프로퍼티를 수정:
        entity.update(dto.getReplyText());
        
    }

    public Reply create(ReplyCreateDto dto) {
        log.info("create(dto ={})", dto);

        // 1. id로 POST 엔터티 검색
        Post post = postRepository.findById(dto.getPostId()).orElseThrow();

        // 2. ReplyCreateDto 객체를 Reply 엔터티 객체로 변환
        Reply entity = Reply.builder().post(post).replyText(dto.getReplyText()).writer(dto.getWriter()).build();

        // 3. DB replies 테이블에 insert
        replyRepository.save(entity);

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Reply> read(Long postId) {
        log.info("read(postId = {})", postId);

        // 1. postId로 Post를 검색.
        // -> 검색을 하고자 하는 연관된 entity 객체를 먼저 찾고
        Post post = postRepository.findById(postId).orElseThrow();

        // 2. 찾은 Post에 달려 있는 댓글 목록을 검색.
        // -> 그 entity 객체를 가지고서 reply를 검색.
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);

        return list;
    }

    @Transactional(readOnly = true)
    public List<Reply> read(Post post) {
        log.info("read(post ={})", post);

        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);

        return list;
    }

    public Long countByPost(Post post) {
        log.info("countByPost(post = {})", post);

        return replyRepository.countByPost(post);
    }
}
