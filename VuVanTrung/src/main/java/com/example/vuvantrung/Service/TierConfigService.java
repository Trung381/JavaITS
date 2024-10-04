package com.example.vuvantrung.Service;

import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Repository.TierConfigRepository;
import org.checkerframework.framework.qual.DefaultQualifierForUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TierConfigService {
    @Autowired
    private TierConfigRepository tierConfigRepository;

    public TierConfig createOrUpdateTierConfig(TierConfig tierConfig){
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

            // Cập nhật các thuộc tính
            existingTierConfig.setMinUsage(updatedTierConfig.getMinUsage());
            existingTierConfig.setMaxUsage(updatedTierConfig.getMaxUsage());
            existingTierConfig.setPrice(updatedTierConfig.getPrice());

            // Lưu lại thay đổi
            return tierConfigRepository.save(existingTierConfig);
        } else {
            throw new Exception("Không tìm thấy TierConfig với ID: " + id);
        }
    }
}

