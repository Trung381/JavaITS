package com.example.vuvantrung.Service;

import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Entity.UsageHistory;
import com.example.vuvantrung.Entity.User;
import com.example.vuvantrung.Exception.UserNotFoundException;
import com.example.vuvantrung.Repository.UsageHistoryRepository;
import com.example.vuvantrung.Repository.UserRepository;
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
    private UserService userService;

    @Autowired
    private TierConfigService tierConfigService;
    @Autowired
    private UserRepository userRepository;

    public double calculateCost(int eUsed){
        List<TierConfig> tiers = tierConfigService.getAllTierConfigs();
        double cost = 0;
        int remaining = eUsed;
        for(TierConfig tier : tiers){
            int usageInTier = Math.min(remaining, tier.getMaxUsage() - tier.getMinUsage()+1);
            cost += usageInTier*tier.getPrice();
            remaining -= usageInTier;
            if (remaining <= 0) {
                break;
            }
        }
        return cost;
    }

    public UsageHistory saveUsage(int eUsed, LocalDate date, int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            double cost = calculateCost(eUsed);
            UsageHistory usageHistory = new UsageHistory(date, eUsed, cost, user.get());
            return usageHistoryRepository.save(usageHistory);
        } else {
            throw new UserNotFoundException("Người dùng với ID: " + userId + " không tồn tại.");
        }
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

            existingUsageHistory.setDate(updatedUsageHistory.getDate());
            existingUsageHistory.seteUsed(updatedUsageHistory.geteUsed());
            existingUsageHistory.setAmount(updatedUsageHistory.getAmount());

            return usageHistoryRepository.save(existingUsageHistory);
        } else {
            throw new Exception("Không tìm thấy UsageHistory với ID: " + id);
        }
    }

    public List<UsageHistory> getUsageHistoryByUsername(Integer id) {
        return usageHistoryRepository.findAllByUserId(id);
    }


}
