package com.example.vuvantrung.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsageHistory is a Querydsl query type for UsageHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsageHistory extends EntityPathBase<UsageHistory> {

    private static final long serialVersionUID = 1234129229L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsageHistory usageHistory = new QUsageHistory("usageHistory");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Integer> eUsed = createNumber("eUsed", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final QUser user;

    public QUsageHistory(String variable) {
        this(UsageHistory.class, forVariable(variable), INITS);
    }

    public QUsageHistory(Path<? extends UsageHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsageHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsageHistory(PathMetadata metadata, PathInits inits) {
        this(UsageHistory.class, metadata, inits);
    }

    public QUsageHistory(Class<? extends UsageHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

