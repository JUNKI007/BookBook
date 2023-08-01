//package com.example.brunchStory.member.service;
//
//import com.example.brunchStory.member.domain.entity.Member;
//import com.example.brunchStory.member.domain.entity.Subscribe;
//import com.example.brunchStory.member.repository.MemberRepository;
//import com.example.brunchStory.member.repository.SubScribeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class SubScribeService {
//    private final SubScribeRepository subScribeRepository;
//    private final MemberRepository memberRepository;
//
//    public void subscribe( Long authorId, Long memberId) {
//        Optional<Member> authorOptional = findMemberById(authorId);
//        Optional<Member> memberOptional = findMemberById(memberId);
//
//        if (authorOptional.isPresent() && memberOptional.isPresent()) {
//            Member author = authorOptional.get();
//            Member member = memberOptional.get();
//            // 현재 시간 정보를 가져옵니다.
//            LocalDateTime subscribeTime = LocalDateTime.now();
//
//            // 구독 정보를 생성하고 저장합니다.
//            Subscribe subscribe = new Subscribe();
//            subscribe.setAuthor(author);
//            subscribe.setMember(member);
//            subscribe.setSubscribeTime(LocalDate.from(subscribeTime));
//            subScribeRepository.save(subscribe);
//        } else {
//            throw new IllegalArgumentException("작가 또는 구독자를 찾을 수 없습니다.");
//        }
//    }
//    //구독중
//    public boolean isSubscribed(Long authorId, Long memberId){
//        return subScribeRepository.existsByMemberIdAndAuthorId(memberId, authorId);
//    }
//
//    //구독 해제
//    public void unSubscribe(Long authorId, Long memberId){
//        subScribeRepository.deleteMemberIdAndAuthorId(memberId, authorId);
//    }
//    //구독자수
//    public Long getSubscriberCount(Long authorId){
//        return subScribeRepository.countByAuthorId(authorId);
//    }
//
//    public List<Subscribe> getSubscriptions(Long memberId, Long authorId) {
//        return subScribeRepository.findByMemberIdAndAuthorId(memberId, authorId);
//    }
//    private Optional<Member> findMemberById(Long memberId) {
//        // memberId를 사용하여 Member를 찾는 로직을 구현합니다.
//        return memberRepository.findById(memberId);
//    }
//
//
//}
//
////    void unsubscribe(Member member, Member author);
////
////    boolean isSubscribed(Member member, Member author);
////
////    int getSubscriberCount(Member author);
