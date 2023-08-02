package com.example.brunchStory.member.domain.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.brunchStory.member.domain.response.QAuthorResponse is a Querydsl Projection type for AuthorResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAuthorResponse extends ConstructorExpression<AuthorResponse> {

    private static final long serialVersionUID = 2038789419L;

    public QAuthorResponse(com.querydsl.core.types.Expression<? extends com.example.brunchStory.member.domain.entity.Member> member) {
        super(AuthorResponse.class, new Class<?>[]{com.example.brunchStory.member.domain.entity.Member.class}, member);
    }

}

