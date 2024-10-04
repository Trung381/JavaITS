package com.example.vuvantrung.Repository;

import com.example.vuvantrung.Entity.TierConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TierConfigRepository extends JpaRepository<TierConfig, Long>{
    // xếp tăng dần để tí dùng trong usageHistoryService còn duyệt qua để tính xiền
    List<TierConfig> findAllByOrderByMinUsageAsc();
}
