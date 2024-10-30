package com.example.vuvantrung.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTierConfig is a Querydsl query type for TierConfig
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTierConfig extends EntityPathBase<TierConfig> {

    private static final long serialVersionUID = -1828280354L;

    public static final QTierConfig tierConfig = new QTierConfig("tierConfig");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxUsage = createNumber("maxUsage", Integer.class);

    public final NumberPath<Integer> minUsage = createNumber("minUsage", Integer.class);

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public QTierConfig(String variable) {
        super(TierConfig.class, forVariable(variable));
    }

    public QTierConfig(Path<? extends TierConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTierConfig(PathMetadata metadata) {
        super(TierConfig.class, metadata);
    }

}

