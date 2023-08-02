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
    private final AlarmService alarmService;
    private final LikesService likesService;


    @PostMapping("/save-writer")
    public void saveWriter(@RequestBody WriterApplyRequest request, @RequestHeader("Authorization") Long memberId) {
        writerApplyService.saveWriter(request, memberId);
    }

    @DeleteMapping("/delete-writer-apply/{applicantId}")
    public void deleteWriterApply(@PathVariable Long applicantId, @RequestHeader("Authorization") Long memberId) {
        writerApplyService.deleteWriterApply(applicantId, memberId);
    }

    @GetMapping("/get-writer-applies")
    public List<WriterApply> getWriterAppliesForMember(@RequestHeader("Authorization") Long memberId) {
        return writerApplyService.getWriterAppliesForMember(memberId);
    }


    @PostMapping("/subscribe/{authorId}/{memberId}")
    public void subscribe(@PathVariable Long authorId, @PathVariable Long memberId) {
        subScribeService.subscribe(authorId, memberId);
    }

    @PostMapping("/unsubscribe/{authorId}/{memberId}")
    public void unsubscribe(@PathVariable Long authorId, @PathVariable Long memberId) {
        subScribeService.unsubscribe(authorId, memberId);
    }

    @GetMapping("/is-subscribed/{authorId}/{memberId}")
    public boolean isSubscribed(@PathVariable Long authorId, @PathVariable Long memberId) {
        return subScribeService.isSubscribed(authorId, memberId);
    }

    @GetMapping("/subscriber-count/{authorId}")
    public Long getSubscriberCount(@PathVariable Long authorId) {
        return subScribeService.getSubscriberCount(authorId);
    }

    @GetMapping("/subscriptions/{memberId}/{authorId}")
    public List<Subscribe> getSubscriptions(@PathVariable Long memberId, @PathVariable Long authorId) {
        return subScribeService.getSubscriptions(memberId, authorId);
    }
    @PostMapping("/approve-writer-apply/{applicantId}")
    public void approveWriterApply(@PathVariable Long applicantId) {
        writerApplyService.approveWriterApply(applicantId);
    }

    @PostMapping("/create-post-alarm/{authorId}/{memberId}")
    public void createPostAlarm(@PathVariable Long authorId, @PathVariable Long memberId) {
        alarmService.createPostAlarm(authorId, memberId);
    }


    @PostMapping("/like/{memberId}/{postId}")
    public void likePost(@PathVariable Long memberId, @PathVariable Long postId) {
        // 잘못된 postId 사용
        likesService.Like(memberId, postId); // postId가 9999인 글은 존재하지 않는다고 가정합니다.
    }

    @GetMapping("/has-liked/{memberId}/{postId}")
    public boolean hasLiked(@PathVariable Long memberId, @PathVariable Long postId) {
        return likesService.hasLiked(memberId, postId);
    }

    @GetMapping("/like-count/{postId}")
    public Long getLikeCount(@PathVariable Long postId) {
        return likesService.getLikeCount(postId);
    }

}
