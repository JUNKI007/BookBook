package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.entity.Member;


public interface SubScribeService {

    void subscribe(Member member, Member author);

    void unsubscribe(Member member, Member author);

    boolean isSubscribed(Member member, Member author);

    int getSubscriberCount(Member author);
}