package com.example.brunchStory.member.service;
import com.example.brunchStory.member.domain.entity.Alarm;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.Subscribe;
import com.example.brunchStory.member.repository.AlarmRepository;
import com.example.brunchStory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

    //글을 올릴 때 알림 생성 및 저장

    public void createPostAlarm(Long authorId) {
        // 작성자 멤버 찾기
        Optional<Member> optionalAuthor = memberRepository.findById(authorId);
        if (optionalAuthor.isEmpty()) {
            // 작성자가 존재하지 않을 경우 에러 처리 (또는 다른 방식의 처리)
            throw new IllegalArgumentException("작성자를 찾을 수 없습니다.");
        }

        Member author = optionalAuthor.get();

        // 작성자의 구독자 목록 조회
        List<Subscribe> subscribers = author.getSubscribers();

        // 작성자의 구독자들에게 알림 생성 및 저장
        for (Subscribe subscriber : subscribers) {
            Member subscriberMember = subscriber.getMember(); // 구독자 정보 가져오기

            // 구독자가 존재하지 않을 경우 에러 처리 (또는 다른 방식의 처리)
            if (subscriberMember == null) {
                throw new IllegalArgumentException("구독자를 찾을 수 없습니다.");
            }

            String message = author.getName() + "님이 새 글을 올렸습니다.";
            Alarm alarm = new Alarm(null, author, subscriberMember);
            alarmRepository.save(alarm);
        }
    }
}
