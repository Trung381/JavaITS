package com.example.vuvantrung.repository;

import com.example.vuvantrung.entity.TierConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TierConfigRepository extends JpaRepository<TierConfig, Long>{
    // xếp tăng dần để tí dùng trong usageHistoryService còn duyệt qua để tính xiền
    List<TierConfig> findAllByOrderByMinUsageAsc();

    @Query(value = "SELECT * FROM tier_config WHERE max_usage = (SELECT MAX(max_usage) FROM tier_config)", nativeQuery = true)
    TierConfig findTierConfigWithMaxUsageNative();
}
