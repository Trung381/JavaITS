package com.example.vuvantrung.repository.blacklistedToken;

import com.example.vuvantrung.entity.BlacklistedToken;
import com.example.vuvantrung.entity.QBlacklistedToken;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public class BlacklistedTokenRepositoryCustomImpl implements BlacklistedTokenRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    @Transactional
    public void deleteExpiredTokensDSL() {
        QBlacklistedToken blacklistedToken = QBlacklistedToken.blacklistedToken;

        JPADeleteClause deleteClause = queryFactory.delete(blacklistedToken)
                .where(blacklistedToken.blacklistedAt.before(
                        // Tính toán thời gian hiện tại trừ đi 1 giờ
                        java.time.LocalDateTime.now().minusHours(1)
                ));

        deleteClause.execute();
    }

    // Bạn có thể triển khai các phương thức tùy chỉnh khác ở đây
}
