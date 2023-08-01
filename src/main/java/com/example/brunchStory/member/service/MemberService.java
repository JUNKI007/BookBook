package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.request.SignupRequest;
import com.example.brunchStory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void insert(SignupRequest request){
        memberRepository.save(request.toEntity());
    }

//    public void delete()


}
