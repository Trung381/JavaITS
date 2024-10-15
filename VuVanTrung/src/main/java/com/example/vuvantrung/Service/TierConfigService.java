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

    public TierConfig deleteTierConfigById(Long id) throws Exception {
        Optional<TierConfig> tierConfigOptional = tierConfigRepository.findById(id);
        if(tierConfigOptional.isPresent()){
            TierConfig tierConfig = tierConfigOptional.get();
            tierConfigRepository.deleteById(id);
            log.warn("tier config has max usage {} has been deleted", tierConfig.getMaxUsage());
            return tierConfig;
        }
        else {
            String s = "ko co tier co id " + id;
            throw new Exception(s);
        }
    }

    public TierConfig updateTierConfig(Long id, TierConfig updatedTierConfig) throws Exception {
        Optional<TierConfig> tierConfigOptional = tierConfigRepository.findById(id);
        if (tierConfigOptional.isPresent()) {
            TierConfig existingTierConfig = tierConfigOptional.get();

            existingTierConfig.setMinUsage(updatedTierConfig.getMinUsage());
            existingTierConfig.setMaxUsage(updatedTierConfig.getMaxUsage());
            existingTierConfig.setPrice(updatedTierConfig.getPrice());

            log.info("updated successfully !\nmin usage: {}\nmax usage: {}\nprice: {}", existingTierConfig.getMinUsage(), existingTierConfig.getMaxUsage(), existingTierConfig.getPrice());
            return tierConfigRepository.save(existingTierConfig);
        } else {
            throw new Exception("Không tìm thấy TierConfig với ID: " + id);
        }
    }
}

