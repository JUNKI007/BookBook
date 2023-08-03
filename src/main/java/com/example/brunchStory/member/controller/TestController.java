package com.example.brunchStory.member.controller;

import com.example.brunchStory.member.domain.entity.Subscribe;
import com.example.brunchStory.member.domain.entity.WriterApply;
import com.example.brunchStory.member.domain.request.WriterApplyRequest;
import com.example.brunchStory.member.service.AlarmService;
import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.member.service.SubScribeService;
import com.example.brunchStory.member.service.WriterApplyService;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.service.LikesService;
import com.example.brunchStory.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final WriterApplyService writerApplyService;
    private final SubScribeService subScribeService;
    //// 위에 두개는 멤버
    private final AlarmService alarmService;
    private final LikesService likesService;
    // 포스트 컨트롤러





    @GetMapping("/is-subscribed/{authorId}/{memberId}")
    public boolean isSubscribed(@PathVariable Long authorId, @PathVariable Long memberId) {
        return subScribeService.isSubscribed(authorId, memberId);
    }

    @GetMapping("/subscriber-count/{authorId}")
    public Long getSubscriberCount(@PathVariable Long authorId) {
        return subScribeService.getSubscriberCount(authorId);
    }

    @GetMapping("/subscriptions/{memberId}/{authorId}")
    public Subscribe getSubscriptions(@PathVariable Long memberId, @PathVariable Long authorId) {
        return subScribeService.getSubscriptions(memberId, authorId);
    }







}
