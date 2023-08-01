package com.example.brunchStory.config.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberLogin is a Querydsl query type for MemberLogin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberLogin extends EntityPathBase<MemberLogin> {

    private static final long serialVersionUID = 199874010L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberLogin memberLogin = new QMemberLogin("memberLogin");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> loginTime = createDate("loginTime", java.time.LocalDate.class);

    public final com.example.brunchStory.member.domain.entity.QMember memberLog;

    public QMemberLogin(String variable) {
        this(MemberLogin.class, forVariable(variable), INITS);
    }

    public QMemberLogin(Path<? extends MemberLogin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberLogin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberLogin(PathMetadata metadata, PathInits inits) {
        this(MemberLogin.class, metadata, inits);
    }

    public QMemberLogin(Class<? extends MemberLogin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberLog = inits.isInitialized("memberLog") ? new com.example.brunchStory.member.domain.entity.QMember(forProperty("memberLog")) : null;
    }

}

