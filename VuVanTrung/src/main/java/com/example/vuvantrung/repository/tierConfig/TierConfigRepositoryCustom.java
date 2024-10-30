package com.example.vuvantrung.repository.tierConfig;

import com.example.vuvantrung.entity.TierConfig;
import java.util.List;

interface TierConfigRepositoryCustom {
    List<TierConfig> findAllByOrderByMinUsageAsc();
    TierConfig findTierConfigWithMaxUsage();
}
