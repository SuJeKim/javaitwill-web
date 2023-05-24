package com.itwill.spring2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Reply;
import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.dto.ReplyReadDto;
import com.itwill.spring2.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final field를 초기화하는 생성자
@Service // spring context의 서비스 컴포넌트 객체로 등록.
public class ReplyService {

    private final ReplyRepository replyRepository; // spring context가 @을 보고 생성 및 초기화 함. -> 생성자에 의한 의존성 주입.
    

    public int create(ReplyCreateDto dto) {
        log.info("create(dto={})", dto);
        
        return replyRepository.insert(dto.toEntity());
    }


    public List<ReplyReadDto> read(long postId) {
        
        log.info("read(postId = {})", postId);
        
        
        List<Reply> list =  replyRepository.selectByPostId(postId);
        
        return list.stream().map(ReplyReadDto::fromEnetity).toList(); 
        // 변경없이 argument를 전달할 경우 사용함.
        // ReplyReadDto객체에 있는 메서드(좌우 변경 가능함.)
        // ReplyReadDto객체에서 메서드 호출이거나 argument(ReplyReadDto.fromEnetity(list.get(x)))를 전달.
        // ReplyReadDto::fromEnetity == (x) -> ReplyReadDto.fromEntity(x)
    }
    
    /**
     * 
     * @param id 댓글 id
     * @return
     */
    public int delete(long id) {
        log.info("delete(id = {})",id);
        
        return replyRepository.delete(id);
    }
    
}
