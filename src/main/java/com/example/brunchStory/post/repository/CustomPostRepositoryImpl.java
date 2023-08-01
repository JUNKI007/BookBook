package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.response.PostResponse;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.example.brunchStory.member.domain.entity.QMember.member;
import static com.example.brunchStory.post.domain.entity.QPost.post;

public class CustomPostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostResponse> findAllByCondition(PageRequest request, PostCondition condition) {

        JPAQuery<Post> posts = queryFactory
                .select(post)
                .from(post)
                .leftJoin(member)
                .fetchJoin()
                .where()
                .offset(request.getPageNumber())
                .limit(request.getPageSize());

        List<Post> postList = posts.fetch();


        Long totalSize = queryFactory
                .select(post.count())
                .from(post)
                .where()
                .fetchOne();
        new PageImpl<>(postList, request, totalSize); // 작업 더 해야함.

        return null;
    }

    public CustomPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}
