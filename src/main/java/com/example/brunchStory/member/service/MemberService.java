package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
//    저장
    public Member createMember(Member member){
       return memberRepository.save(member);
    }
//    멤버아이디
    public Member getMemberById(Long memberId){
        return memberRepository.findById(memberId).orElse(null);
    }
// findAll
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
}
