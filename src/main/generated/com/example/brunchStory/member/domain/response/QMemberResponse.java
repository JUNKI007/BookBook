package com.example.brunchStory.member.domain.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.brunchStory.member.domain.response.QMemberResponse is a Querydsl Projection type for MemberResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberResponse extends ConstructorExpression<MemberResponse> {

    private static final long serialVersionUID = -375103686L;

    public QMemberResponse(com.querydsl.core.types.Expression<? extends com.example.brunchStory.member.domain.entity.Member> member) {
        super(MemberResponse.class, new Class<?>[]{com.example.brunchStory.member.domain.entity.Member.class}, member);
    }

}

