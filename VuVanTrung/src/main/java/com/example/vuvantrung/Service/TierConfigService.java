package com.example.vuvantrung.Service;

import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Repository.TierConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TierConfigService {
    @Autowired
    private TierConfigRepository tierConfigRepository;

    public TierConfig createTierConfig(TierConfig tierConfig){
        return tierConfigRepository.save(tierConfig);
    }

    public List<TierConfig> getAllTierConfigs(){
        return tierConfigRepository.findAllByOrderByMinUsageAsc();
    }

    public TierConfig deleteTierConfigById(Long id) {
        Optional<TierConfig> tierConfigOptional = tierConfigRepository.findById(id);
        if (tierConfigOptional.isEmpty()) {
            throw new RuntimeException("TierConfig with ID " + id + " not found.");
        }
        TierConfig tierConfig = tierConfigOptional.get();
        tierConfigRepository.deleteById(id);
        log.warn("TierConfig with max usage {} has been deleted", tierConfig.getMaxUsage());
        return tierConfig;
    }

    public TierConfig updateTierConfig(Long id, TierConfig updatedTierConfig) {
        Optional<TierConfig> tierConfigOptional = tierConfigRepository.findById(id);
        if (tierConfigOptional.isEmpty()) {
            throw new RuntimeException("TierConfig with ID " + id + " not found.");
        }
        TierConfig existingTierConfig = tierConfigOptional.get();

        existingTierConfig.setMinUsage(updatedTierConfig.getMinUsage());
        existingTierConfig.setMaxUsage(updatedTierConfig.getMaxUsage());
        existingTierConfig.setPrice(updatedTierConfig.getPrice());

        log.info("Updated successfully! Min usage: {}, Max usage: {}, Price: {}",
                existingTierConfig.getMinUsage(), existingTierConfig.getMaxUsage(), existingTierConfig.getPrice());

        return tierConfigRepository.save(existingTierConfig);
    }

    public TierConfig getTierConfigWithMaxUsage() {
        // Business logic to get tier config with max usage
        return tierConfigRepository.findTierConfigWithMaxUsageNative();
    }
}
