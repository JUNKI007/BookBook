package com.example.bookBook.member.service;
import com.example.bookBook.member.domain.entity.Alarm;
import com.example.bookBook.member.domain.entity.Member;
import com.example.bookBook.member.repository.AlarmRepository;
import com.example.bookBook.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

    //글을 올릴 때 알림 생성 및 저장

    public void createPostAlarm(Long authorId, Long memberId) {
        Member author = findMemberById(authorId);
        Member member = findMemberById(memberId);
        if (author != null && member != null) {
            Alarm alarm = new Alarm();
            alarm.setAuthor(author);
            alarm.setMember(member);
            alarmRepository.save(alarm);
       }else {
           throw new IllegalArgumentException("작가 또는 구독자를 찾을 수 없습니다.");
       }
    }
    // 멤버 찾기
    private Member findMemberById(Long memberId){
        return memberRepository.findById(memberId).orElse(null);
    }
}
