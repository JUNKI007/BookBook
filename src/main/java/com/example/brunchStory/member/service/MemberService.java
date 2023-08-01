package com.example.brunchStory.member.service;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.config.exception.LoginFailException;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.request.LoginRequest;
import com.example.brunchStory.member.domain.response.AuthorResponse;
import com.example.brunchStory.member.domain.response.LoginResponse;
import com.example.brunchStory.member.domain.response.MemberResponse;
import com.example.brunchStory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.brunchStory.member.domain.request.SignupRequest;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final AuthService authService;



    public LoginResponse login(LoginRequest request) {
        Optional<Member> byUserIdAndPassword =
                memberRepository
                        .findByUserIdAndPassword(request.userId(), request.password());
        Member member = byUserIdAndPassword
                .orElseThrow(() -> new LoginFailException("존재하지 않는 유저입니다"));

        String token = authService.makeToken(member);
        return new LoginResponse(member.getId(), member.getEmail(), member.getRole(), token);
    }

    public Map<String, Object> getTokenToData(String token) {
        return authService.getClaims(token);
    }


    // 회원가입
    public void insert(SignupRequest request){
        memberRepository.save(request.toEntity());
    }

    // 회원삭제
    public void delete(Long id){
        memberRepository.deleteById(id);
    }
    // 일반멤버찾기
    public MemberResponse findByMember(Long id){
        Optional<Member> byId = memberRepository.findById(id);

        Member member = byId.orElseThrow(RuntimeException::new);
        MemberResponse memberResponse = new MemberResponse(member);

        return memberResponse;
    }
    // 저자찾기
    public AuthorResponse findByAuthor(Long id){
        Optional<Member> byId = memberRepository.findById(id);
        Member member = byId.orElseThrow(RuntimeException::new);
        AuthorResponse authorResponse = new AuthorResponse(member);
        return authorResponse;
    }

    // 전체멤버찾기
    public Page<MemberResponse> findAllMember(PageRequest pageRequest){
        Page<Member> memberAll = memberRepository.findAllBy(pageRequest);
        return memberAll.map(MemberResponse::new);
    }

}
