package com.example.vuvantrung.Repository;

import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Entity.UsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Long> {

}
