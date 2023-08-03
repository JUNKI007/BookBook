package com.example.brunchStory.member.domain.response;

import lombok.Getter;

import java.util.Map;

@Getter
public class MemberInterestResponse {
    private Map<String, String> interestRatio;

    public MemberInterestResponse(Map<String, String> interestRatio) {
        this.interestRatio = interestRatio;
    }
}
