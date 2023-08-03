package com.example.brunchStory.member.service;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.Subscribe;
import com.example.brunchStory.member.domain.response.MemberResponse;
import com.example.brunchStory.member.repository.SubScribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@RequiredArgsConstructor
public class SubScribeService {
    private final SubScribeRepository subScribeRepository;
    private final MemberService memberService;
    public void subscribe(Long authorId, Long memberId) {
        Member author = memberService.findById(authorId);
        Member member = Member.builder().id(memberId).build();
        Optional<Subscribe> byMemberAndAuthor = subScribeRepository.findByMemberAndAuthor(author, member);
        Subscribe data = byMemberAndAuthor.orElse(null);
        if(data == null) {
            // 현재 시간 정보를 가져옵니다.
            LocalDate subscribeTime = LocalDate.now();
            // 구독 정보를 생성하고 저장합니다.
            Subscribe subscribe = new Subscribe();
            subscribe.setMember(member);
            subscribe.setAuthor(author);
            subscribe.setSubscribeTime(subscribeTime);
            subScribeRepository.save(subscribe);
        }else {
            subScribeRepository.deleteByAuthorAndMember(author, member);
        }
    }
    public void unsubscribe(Long authorId, Long memberId) {
        Member author = memberService.findById(authorId);
        Member member = Member.builder().id(memberId).build();
        subScribeRepository.deleteByAuthorAndMember(author, member);
    }
    public boolean isSubscribed(Long authorId, Long memberId) {
        Member author= memberService.findById(authorId);
        Member member = Member.builder().id(memberId).build();
        return subScribeRepository.existsByAuthorAndMember(author, member);
    }
    public Long getSubscriberCount(Long authorId) {
        Member author = memberService.findById(authorId);
        return subScribeRepository.countByAuthor(author);
    }
    // 이거 나누기
    // 작가를 찔러서 작가를 구독한 리스트를 불러온다.
    // member 받아온다.
    public Subscribe getSubscriptions(Long memberId, Long authorId) {
        Member author = memberService.findById(authorId);
        Member member = Member.builder().id(memberId).build();
        Optional<Subscribe> byMemberAndAuthor =
                subScribeRepository
                        .findByMemberAndAuthor(author, member);
        return byMemberAndAuthor.orElse(null);
    }
}