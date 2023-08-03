package com.example.brunchStory.member.service;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.config.exception.LoginFailException;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.request.LoginRequest;
import com.example.brunchStory.member.domain.response.AuthorResponse;
import com.example.brunchStory.member.domain.response.LoginResponse;
import com.example.brunchStory.member.domain.response.MemberAllResponse;
import com.example.brunchStory.member.domain.response.MemberResponse;
import com.example.brunchStory.member.repository.InterestRepository;
import com.example.brunchStory.member.repository.MemberRepository;
import com.example.brunchStory.member.repository.SubScribeRepository;
import com.example.brunchStory.post.domain.entity.Interest;
import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.brunchStory.member.domain.request.SignupRequest;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final InterestRepository interestRepository;
    private final SubScribeRepository subScribeRepository;
    private final AuthService authService;
    private final SubjectService subjectService;

    public LoginResponse login(LoginRequest request) {
        Optional<Member> byUserIdAndPassword =
                memberRepository
                        .findByUserIdAndPassword(request.userId(), request.password());
        Member member = byUserIdAndPassword
                .orElseThrow(() -> new LoginFailException("존재하지 않는 유저입니다"));

        String token = authService.makeToken(member);
        return new LoginResponse(member.getId(), member.getEmail(), member.getRole(), token);
    }

    // 회원가입
    public void insert(SignupRequest request){
        // 회원 정보 저장
        Member saveMember = memberRepository.save(request.toEntity());

        // subject 아이디 리스트로 전체 조회하고 하나의 리스트로 저장
        List<Subject> allByIds = subjectService.findAllByIds(request.subject());
                                            // request.subject()는 subject id 리스트를 가지고 있음

        // Inteset entity 리스트 선언
        List<Interest> interests = new ArrayList<>();

        for (Subject subject:
             allByIds) {
            // Inteset entity 형식에 맞취서 member,subject를 list로 저장
            interests.add(new Interest(null,saveMember,subject));
        }
        // interest list를 한 번에 저장
        interestRepository.saveAll(interests);

    }

    // 회원삭제
    public void delete(Long id){
        memberRepository.deleteById(id);
    }

    // 리스폰스 말고 그냥 멤버 찾을때 사용
    public Member findById(Long id){
        Optional<Member> byId = memberRepository.findById(id);
        Member member = byId.orElseThrow(RuntimeException::new);

        return member;
    }

    // 일반멤버찾기
    public MemberResponse findByMember(Long id){
        Optional<Member> byId = memberRepository.findByMember(id);

        Member member = byId.orElseThrow(RuntimeException::new);
        MemberResponse memberResponse = new MemberResponse(member);

        return memberResponse;
    }

    // 저자찾기
    public AuthorResponse findByAuthor(Long id){
        Optional<Member> byId = memberRepository.findByAuthor(id);
        Member member = byId.orElseThrow(RuntimeException::new);
        AuthorResponse authorResponse = new AuthorResponse(member);
        return authorResponse;
    }


    // 전체멤버찾기


    public Page<MemberAllResponse> findAllMember(PageRequest pageRequest){
        Page<Member> memberAll = memberRepository.findAllMember(pageRequest);
        return memberAll.map(MemberAllResponse::new);
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public List<MemberAllResponse> findAllMemberForMail(){
        List<Member> allMemberForMail = memberRepository.findAllMemberForMail();

        return allMemberForMail.stream().map(MemberAllResponse::new).toList();
    }

    public List<Member> getSubscribingMembers(Long authorId) {
        return subScribeRepository.findMembersByAuthorId(authorId);
    }
}
