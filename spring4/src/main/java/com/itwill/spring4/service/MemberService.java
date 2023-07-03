package com.itwill.spring4.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwill.spring4.dto.member.MemberSignUpDto;
import com.itwill.spring4.repository.member.Member;
import com.itwill.spring4.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
// Security Filter Chain에서 UserDetailsService 객체를 사용할 수 있도록 하기 위해서.
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    // SecurityConfig에서 설정한 PasswordEncoder 빈(bean)을 주입해줌.
    // -> SecurityConfig에서 Bean으로 관리가 되어 있기에 스프링 컨테이너는 다음을 생성하고 있어서 필요한 곳에 넣어줌.
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    public Long registerMember(MemberSignUpDto dto) {
        log.info("registerMember(dto={})", dto);

        Member entity = Member.builder().username(dto.getUsername()).password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail()).build();

        log.info("save 전: entity= {}", entity);

        memberRepository.save(entity);
        log.info("save 후: entity={}", entity);

        return entity.getId(); // DB에 저장된 ID(고유키)를 리턴.
    }

    // Security Filter Chain에서는 loadUserByUsername를 호출하여 Db에 존재하는지를 판단. 
    // -> 해당 메서드가 있어야만 Security Filter Chain가 제대로 동작이 될 수 있음.
    // -> 로그인 성공 여부 판단.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername(username = {})", username);

        // DB에서 username으로 사용자 정보 검색(select).
        // 다형성
        UserDetails user = memberRepository.findByUsername(username);

        if (user != null) {
            return user;
        }

        // 에러 메시지
        throw new UsernameNotFoundException(username + "not found");
    }

}
