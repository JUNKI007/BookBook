package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.Subscribe;
import com.example.brunchStory.member.repository.SubScribeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class SubScribeServiceImpl implements SubScribeService {
    private final SubScribeRepository subScribeRepository;


    @Override
    public void subscribe(Member member, Member author) {
        //이미 구독 중인지 확인
        if (!isSubscribed(member, author)){
            Subscribe subscribe  = new Subscribe();
            subscribe.setMember(member);
            subscribe.setAuthor(author);
            subscribe.setSubscribeTime(LocalDate.now());
            subScribeRepository.save(subscribe);
        }
    }

    @Override
    public void unsubscribe(Member member, Member author) {
        // 해당 회원(Member)이 특정 저자(author)의 구독을 조회
        Subscribe subscribe = subScribeRepository.findByMemberAndAuthor(member, author);
        if (subscribe != null) {
            // 구독이 존재하면 해당 구독을 삭제
            subScribeRepository.delete(subscribe);
        }
    }

    @Override
    public boolean isSubscribed(Member member, Member author) {
        // 해당 회원이 특정 저자를 구독중인지 확인
        return subScribeRepository.existsByMemberAndAuthor(member, author);
        // existsByMemberAndAuthor : subscribeRepository 를 통해서 특정회원 과 저자 간의 구독정보가 존재하는지 확인
        // 이를 위해 SubscribeRepository 인터페이스에 정의된 existsByMemberAndAuthor 메서드를 사용
        // existsByMemberAndAuthor 결과가 'ture'면 해당 회원이 해당저자를 구독중이라는 뜻 이므로
        // true 반환, false면 반대로
    }

    @Override
    public int getSubscriberCount(Member author) {
        // 구독자수 조회
        return subScribeRepository.countByAuthor(author);
    }
}
