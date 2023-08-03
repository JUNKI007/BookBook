package com.example.brunchStory.member.service;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.config.exception.LoginFailException;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.request.LoginRequest;
import com.example.brunchStory.member.domain.response.*;
import com.example.brunchStory.member.repository.InterestRepository;
import com.example.brunchStory.member.repository.MemberRepository;
import com.example.brunchStory.member.repository.SubScribeRepository;
import com.example.brunchStory.post.domain.entity.Interest;
import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.*;


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

        return byId.orElseThrow(RuntimeException::new);
    }

    // 일반멤버찾기
    public MemberResponse findByMember(Long id){
        Optional<Member> byId = memberRepository.findByMember(id);

        Member member = byId.orElseThrow(RuntimeException::new);

        return new MemberResponse(member);
    }

    // 저자찾기
    public AuthorResponse findByAuthor(Long id){
        Optional<Member> byId = memberRepository.findByAuthor(id);
        Member member = byId.orElseThrow(RuntimeException::new);
        return new AuthorResponse(member);
    }

    // 전체멤버찾기
    public Page<MemberAllResponse> findAllMember(PageRequest pageRequest){
        Page<Member> memberAll = memberRepository.findAllMember(pageRequest);
        return memberAll.map(MemberAllResponse::new);
    }


    // 관심사 순위 및 비율 찾기
    public MemberInterestResponse findInterestRank(){
        List<Interest> all = interestRepository.findAll();

        List<String> subjectName = new ArrayList<>();
        // subject name을 리스트로 변환
        for (Interest interest : all) {
            subjectName.add(interest.getSubject().getName());
        }

        // 각 아이디의 빈도 계산
        Map<String, Integer> subjectCnt = new HashMap<>();
        for (String id : subjectName) {
            subjectCnt.put(id, subjectCnt.getOrDefault(id, 0) + 1);
            // 존재하면 키를 통해서 값을 가져와서 +1을 하고 존재하지 않으면 0을 반환하고 거기에 +1을 한다.
        }

        // 각 아이디의 비율 계산
        int totalIds = subjectName.size();
        Map<String, String> idRatioMap = new HashMap<>();
        for (String id : subjectCnt.keySet()) { // key 값을 꺼내려고 keySet을 사용함
            int frequency = subjectCnt.get(id); // 해당 값의 개수
            double ratio = ((double) frequency / totalIds) * 100; // 비율을 퍼센트로 표현
            // 총 개수에서 해당 값의 개수를 나누어 비율 구함
            ratio = Math.round(ratio * 10.0) / 10.0; // 소수점 이하 한 자리까지 반올림
            String ratioString = Double.toString(ratio) + "%"; // String으로 변환하고 %추가
            idRatioMap.put(id, ratioString); // 해당 아이디에 비율 삽입
        }

        return new MemberInterestResponse(idRatioMap);

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
