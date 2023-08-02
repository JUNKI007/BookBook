package com.example.brunchStory.member.service;
import com.example.brunchStory.member.domain.entity.Alarm;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.response.MemberResponse;
import com.example.brunchStory.member.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberService memberService;

    //글을 올릴 때 알림 생성 및 저장

    public void createPostAlarm(Long authorId, Long memberId) {
        Member author = memberService.findById(authorId);
        Member member = Member.builder().id(memberId).build();


        Alarm alarm = new Alarm();
        alarm.setMember(member);
        alarm.setAuthor(author);
        alarmRepository.save(alarm);

    }

}
