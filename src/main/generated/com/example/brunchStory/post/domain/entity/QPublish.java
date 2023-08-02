package com.example.brunchStory.post.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublish is a Querydsl query type for Publish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPublish extends EntityPathBase<Publish> {

    private static final long serialVersionUID = 1219071064L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublish publish = new QPublish("publish");

    public final QBook book;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPost post;

    public QPublish(String variable) {
        this(Publish.class, forVariable(variable), INITS);
    }

    public QPublish(Path<? extends Publish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublish(PathMetadata metadata, PathInits inits) {
        this(Publish.class, metadata, inits);
    }

    public QPublish(Class<? extends Publish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book"), inits.get("book")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

