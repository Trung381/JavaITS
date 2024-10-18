package com.example.vuvantrung.repository;

import com.example.vuvantrung.entity.UsageHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Long> {
    @Query("SELECT b FROM UsageHistory b WHERE b.user.id = :id")
    List<UsageHistory> findAllByUserId(@Param("id") Integer id);

    List<UsageHistory> findByUserIsNotNull();

    @Query(value = "select * from usage_history where user_id = :id and status = 0", nativeQuery = true)
    List<UsageHistory> findAllByUserIdAndNotPaid(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "update usage_history set status = 1 where user_id = :id and status = 0 and month(date) = :m", nativeQuery = true)
    void payElectricityBillHasNotPaidByUserIdAndMonth(@Param("id") Integer id, @Param("m") int m);

    @Query(value = "select * from usage_history where user_id = :id and status = 1 and month(date) = :m", nativeQuery = true)
    UsageHistory findElectricityBillHasPaidByUserIdAndMonth(@Param("id") Integer id, @Param("m") int m);

    @Query(value = "select * from usage_history where user_id = :id and month(date) = :m", nativeQuery = true)
    Optional<UsageHistory> findElectricityBillByUserIdAndMonth(@Param("id") Integer id, @Param("m") int m);
}
