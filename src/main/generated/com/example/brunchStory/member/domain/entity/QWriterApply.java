package com.example.brunchStory.member.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWriterApply is a Querydsl query type for WriterApply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWriterApply extends EntityPathBase<WriterApply> {

    private static final long serialVersionUID = -825059074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWriterApply writerApply = new QWriterApply("writerApply");

    public final QMember applicant;

    public final BooleanPath approved = createBoolean("approved");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> localDate = createDate("localDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public QWriterApply(String variable) {
        this(WriterApply.class, forVariable(variable), INITS);
    }

    public QWriterApply(Path<? extends WriterApply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWriterApply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWriterApply(PathMetadata metadata, PathInits inits) {
        this(WriterApply.class, metadata, inits);
    }

    public QWriterApply(Class<? extends WriterApply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.applicant = inits.isInitialized("applicant") ? new QMember(forProperty("applicant")) : null;
    }

}

