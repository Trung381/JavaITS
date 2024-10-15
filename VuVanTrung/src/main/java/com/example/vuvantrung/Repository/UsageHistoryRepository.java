package com.example.vuvantrung.Repository;

import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Entity.UsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Long> {
    @Query("SELECT b FROM UsageHistory b WHERE b.user.id = :id")
    List<UsageHistory> findAllByUserId(@Param("id") Integer id);

    List<UsageHistory> findByUserIsNotNull();
}
