package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.entity.Post;

import static com.example.brunchStory.post.domain.entity.QPost.post;
import static com.example.brunchStory.member.domain.entity.QMember.member;


import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.domain.response.PostResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public class CustomPostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostResponse> findAllByCondition(PageRequest request, PostCondition condition,List<Subject> allByIds) {

        JPAQuery<Post> posts = queryFactory
                .select(post)
                .from(post)
                .leftJoin(post.author)
                .fetchJoin()
                .where(eqInterests(allByIds),
                        contentContains(condition.getContent()),
                        titleEq(condition.getTitle()),
                        isLikeGoe(condition.getLikeGoe()),
                        isLikeLoe(condition.getLikeLoe())
                        )
                .offset(request.getPageNumber())
                .limit(request.getPageSize());

        List<Post> postList = posts.fetch();


        Long totalSize = queryFactory
                .select(post.count())
                .from(post)
                .where(eqInterests(allByIds),
                        contentContains(condition.getContent()),
                        titleEq(condition.getTitle()),
                        isLikeGoe(condition.getLikeGoe()),
                        isLikeLoe(condition.getLikeLoe()
                        ))
                .fetchOne();
        PageImpl<Post> postsList = new PageImpl<>(postList, request, totalSize);// 작업 더 해야함.

        Page<PostResponse> map = postsList.map(PostResponse::new);

        return map;
    }

    public CustomPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private BooleanBuilder eqInterests(List<Subject> interests){
        if(interests == null){
            return null;
        }

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (Subject subject : interests){
            booleanBuilder.or(post.subject.eq(subject));
        }

        return booleanBuilder;
    }

    private BooleanExpression contentContains(String content) {
        return content == null
                ? null
                : post.content.contains(content);
    }
    private BooleanExpression titleEq(String title) {
        return title == null
                ? null
                : post.title.eq(title);
    }

    private BooleanExpression isLikeGoe(Integer likeGoe) {
        return likeGoe == null
                ? null
                : post.likeCount.goe(likeGoe);
    }

    private BooleanExpression isLikeLoe(Integer likeLoe) {
        return likeLoe == null
                ? null
                : post.likeCount.loe(likeLoe);
    }

}
