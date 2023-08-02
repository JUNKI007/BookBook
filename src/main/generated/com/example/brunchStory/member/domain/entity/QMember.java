package com.example.brunchStory.member.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1578046071L;

    public static final QMember member = new QMember("member1");

    public final ListPath<com.example.brunchStory.post.domain.entity.Book, com.example.brunchStory.post.domain.entity.QBook> books = this.<com.example.brunchStory.post.domain.entity.Book, com.example.brunchStory.post.domain.entity.QBook>createList("books", com.example.brunchStory.post.domain.entity.Book.class, com.example.brunchStory.post.domain.entity.QBook.class, PathInits.DIRECT2);

    public final ListPath<com.example.brunchStory.post.domain.entity.Comment, com.example.brunchStory.post.domain.entity.QComment> comments = this.<com.example.brunchStory.post.domain.entity.Comment, com.example.brunchStory.post.domain.entity.QComment>createList("comments", com.example.brunchStory.post.domain.entity.Comment.class, com.example.brunchStory.post.domain.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.brunchStory.post.domain.entity.Interest, com.example.brunchStory.post.domain.entity.QInterest> interests = this.<com.example.brunchStory.post.domain.entity.Interest, com.example.brunchStory.post.domain.entity.QInterest>createList("interests", com.example.brunchStory.post.domain.entity.Interest.class, com.example.brunchStory.post.domain.entity.QInterest.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<com.example.brunchStory.post.domain.entity.Post, com.example.brunchStory.post.domain.entity.QPost> posts = this.<com.example.brunchStory.post.domain.entity.Post, com.example.brunchStory.post.domain.entity.QPost>createList("posts", com.example.brunchStory.post.domain.entity.Post.class, com.example.brunchStory.post.domain.entity.QPost.class, PathInits.DIRECT2);

    public final ListPath<Subscribe, QSubscribe> publishers = this.<Subscribe, QSubscribe>createList("publishers", Subscribe.class, QSubscribe.class, PathInits.DIRECT2);

    public final EnumPath<com.example.brunchStory.member.domain.dto.MemberRole> role = createEnum("role", com.example.brunchStory.member.domain.dto.MemberRole.class);

    public final ListPath<Subscribe, QSubscribe> subscribers = this.<Subscribe, QSubscribe>createList("subscribers", Subscribe.class, QSubscribe.class, PathInits.DIRECT2);

    public final ListPath<Alarm, QAlarm> tellNewPost = this.<Alarm, QAlarm>createList("tellNewPost", Alarm.class, QAlarm.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

