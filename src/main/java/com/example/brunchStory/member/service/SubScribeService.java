package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.Subscribe;
import com.example.brunchStory.member.repository.MemberRepository;
import com.example.brunchStory.member.repository.SubScribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubScribeService {
    private final SubScribeRepository subScribeRepository;
    private final MemberRepository memberRepository;

    public void subscribe(Member author, Member member) {
        // 현재 시간 정보를 가져옵니다.
        LocalDate subscribeTime = LocalDate.now();

        // 구독 정보를 생성하고 저장합니다.
        Subscribe subscribe = new Subscribe();
        subscribe.setAuthor(author);
        subscribe.setMember(member);
        subscribe.setSubscribeTime(subscribeTime);
        subScribeRepository.save(subscribe);
    }

    public void unsubscribe(Member author, Member member) {
        subScribeRepository.deleteByAuthorAndMember(author, member);
    }

    public boolean isSubscribed(Member author, Member member) {
        return subScribeRepository.existsByAuthorAndMember(author, member);
    }

    public Long getSubscriberCount(Member author) {
        return subScribeRepository.countByAuthor(author);
    }

    public List<Subscribe> getSubscriptions(Member member, Member author) {
        return subScribeRepository.findByMemberAndAuthor(member, author);
    }

    private Optional<Member> findMemberById(Long memberId) {
        // memberId를 사용하여 Member를 찾는 로직을 구현합니다.
        return memberRepository.findById(memberId);
    }

    public void validateMemberExists(Long memberId) {
        if (findMemberById(memberId).isEmpty()) {
            throw new IllegalArgumentException("해당 멤버를 찾을 수 없습니다.");
        }
    }

    public void validateAuthorExists(Long authorId) {
        if (findMemberById(authorId).isEmpty()) {
            throw new IllegalArgumentException("해당 작가를 찾을 수 없습니다.");
        }
    }
}
