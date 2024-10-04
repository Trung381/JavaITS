package com.example.vuvantrung.Service;

import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Entity.UsageHistory;
import com.example.vuvantrung.Repository.UsageHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsageHistoryService {
    @Autowired
    private UsageHistoryRepository usageHistoryRepository;

    @Autowired
    private TierConfigService tierConfigService;

    public UsageHistory caculateAndSaveUSage(int eUsed, LocalDate date){
        List<TierConfig> tiers = tierConfigService.getAllTierConfigs();
        double cost = 0;
        int remaining = eUsed;
        for(TierConfig tier : tiers){
//            if(remaining > 0 && remaining > tier.getMinUsage()){
//                int usageInTier = (tier.getMaxUsage() > 0) ? Math.min(remaining, tier.getMaxUsage() - tier.getMinUsage()) : remaining; // Nếu maxUsage là 0 (hoặc không giới hạn), dùng toàn bộ remaining
//                cost += tier.getPrice()*usageInTier;
//                remaining -= usageInTier;
//            }
            int usageInTier = Math.min(remaining, tier.getMaxUsage() - tier.getMinUsage()+1);
            cost += usageInTier*tier.getPrice();
            remaining -= usageInTier;
            if (remaining <= 0) {
                break;  // Nếu kWh còn lại đã hết, dừng vòng lặp
            }
        }

        UsageHistory usageHistory = new UsageHistory(date, eUsed, cost);

//        UsageHistory usageHistory = new UsageHistory();
//        usageHistory.setDate(date);
//        usageHistory.seteUsed(eUsed);
//        usageHistory.setAmount(cost);

        return usageHistoryRepository.save(usageHistory);
    }

    public List<UsageHistory> getAllUsageHistory(){
        return usageHistoryRepository.findAll();
    }

    public UsageHistory deleteUsageHistoryById(Long id) throws Exception {
        Optional<UsageHistory> usageHistoryOptional = usageHistoryRepository.findById(id);
        if(usageHistoryOptional.isPresent()){
            UsageHistory usageHistory = usageHistoryOptional.get();
            usageHistoryRepository.deleteById(id);
            return usageHistory;
        }
        else{
            throw new Exception("Lịch sử sử dụng điện không tồn tại với ID: " + id);
        }
    }

    public UsageHistory updateUsageHistory(Long id, UsageHistory updatedUsageHistory) throws Exception {
        Optional<UsageHistory> usageHistoryOptional = usageHistoryRepository.findById(id);
        if (usageHistoryOptional.isPresent()) {
            UsageHistory existingUsageHistory = usageHistoryOptional.get();

            // Cập nhật các thuộc tính
            existingUsageHistory.setDate(updatedUsageHistory.getDate());
            existingUsageHistory.seteUsed(updatedUsageHistory.geteUsed());
            existingUsageHistory.setAmount(updatedUsageHistory.getAmount());

            // Lưu lại thay đổi
            return usageHistoryRepository.save(existingUsageHistory);
        } else {
            throw new Exception("Không tìm thấy UsageHistory với ID: " + id);
        }
    }
}
