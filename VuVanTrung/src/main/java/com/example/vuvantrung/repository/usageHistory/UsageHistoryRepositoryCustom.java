package com.example.vuvantrung.repository.usageHistory;

import com.example.vuvantrung.entity.UsageHistory;
import java.util.List;
import java.util.Optional;

public interface UsageHistoryRepositoryCustom {
    List<UsageHistory> findAllByUserId(Integer id);
    List<UsageHistory> findAllByUserIdAndNotPaid(Integer id);
    void payElectricityBillHasNotPaidByUserIdAndMonth(Integer id, int month);
    UsageHistory findElectricityBillHasPaidByUserIdAndMonth(Integer id, int month);
    Optional<UsageHistory> findElectricityBillByUserIdAndMonth(Integer id, int month);
}