package com.example.vuvantrung.repository.usageHistory;

import com.example.vuvantrung.entity.QUsageHistory;
import com.example.vuvantrung.entity.UsageHistory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UsageHistoryRepositoryImpl implements UsageHistoryRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<UsageHistory> findAllByUserId(Integer id) {
        QUsageHistory qUsageHistory = QUsageHistory.usageHistory;
        return queryFactory.selectFrom(qUsageHistory)
                .where(qUsageHistory.user.id.eq(id))
                .fetch();
    }

    @Override
    public List<UsageHistory> findAllByUserIdAndNotPaid(Integer id) {
        QUsageHistory qUsageHistory = QUsageHistory.usageHistory;
        return queryFactory.selectFrom(qUsageHistory)
                .where(qUsageHistory.user.id.eq(id)
                        .and(qUsageHistory.status.eq(0)))
                .fetch();
    }

    @Override
    @Transactional
    public void payElectricityBillHasNotPaidByUserIdAndMonth(Integer id, int month) {
        QUsageHistory qUsageHistory = QUsageHistory.usageHistory;
        queryFactory.update(qUsageHistory)
                .set(qUsageHistory.status, 1)
                .where(qUsageHistory.user.id.eq(id)
                        .and(qUsageHistory.status.eq(0))
                        .and(qUsageHistory.date.month().eq(month)))
                .execute();
    }

    @Override
    public UsageHistory findElectricityBillHasPaidByUserIdAndMonth(Integer id, int month) {
        QUsageHistory qUsageHistory = QUsageHistory.usageHistory;
        return queryFactory.selectFrom(qUsageHistory)
                .where(qUsageHistory.user.id.eq(id)
                        .and(qUsageHistory.status.eq(1))
                        .and(qUsageHistory.date.month().eq(month)))
                .fetchOne();
    }

    @Override
    public Optional<UsageHistory> findElectricityBillByUserIdAndMonth(Integer id, int month) {
        System.err.println("hjjs c" + month);
        QUsageHistory qUsageHistory = QUsageHistory.usageHistory;
        UsageHistory usageHistory = queryFactory.selectFrom(qUsageHistory)
                .where(qUsageHistory.user.id.eq(id)
                        .and(qUsageHistory.date.month().eq(month)))
                .fetchOne();
        return Optional.ofNullable(usageHistory);
    }
}

