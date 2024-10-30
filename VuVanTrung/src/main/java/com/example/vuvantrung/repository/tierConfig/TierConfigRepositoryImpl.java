package com.example.vuvantrung.repository.tierConfig;

import com.example.vuvantrung.entity.QTierConfig;
import com.example.vuvantrung.entity.TierConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TierConfigRepositoryImpl implements TierConfigRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<TierConfig> findAllByOrderByMinUsageAsc() {
        QTierConfig qTierConfig = QTierConfig.tierConfig;
        return queryFactory.selectFrom(qTierConfig)
                .orderBy(qTierConfig.minUsage.asc())
                .fetch();
    }

    @Override
    public TierConfig findTierConfigWithMaxUsage() {
        QTierConfig qTierConfig = QTierConfig.tierConfig;
        return queryFactory.selectFrom(qTierConfig)
                .orderBy(qTierConfig.maxUsage.desc())
                .fetchFirst();
    }
}
