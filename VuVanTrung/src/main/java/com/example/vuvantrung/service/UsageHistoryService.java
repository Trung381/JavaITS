package com.example.vuvantrung.service;

import com.example.vuvantrung.DTO.Response;
import com.example.vuvantrung.DTO.ResponseFactory;
import com.example.vuvantrung.entity.TierConfig;
import com.example.vuvantrung.entity.UsageHistory;
import com.example.vuvantrung.entity.User;
import com.example.vuvantrung.exception.UserNotFoundException;
import com.example.vuvantrung.repository.UsageHistoryRepository;
import com.example.vuvantrung.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsageHistoryService {
    @Autowired
    private UsageHistoryRepository usageHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TierConfigService tierConfigService;

    public double calculateCost(int eUsed) {
        List<TierConfig> tiers = tierConfigService.getAllTierConfigs();
        double cost = 0;
        int remaining = eUsed;
        TierConfig tierHasMaxUsage = tierConfigService.getTierConfigWithMaxUsage();
        for (TierConfig tier : tiers) {
            int usageInTier;
            if (tier.getMaxUsage() != tierHasMaxUsage.getMaxUsage()) {
                usageInTier = Math.min(remaining, tier.getMaxUsage() - tier.getMinUsage() + 1);
                cost += usageInTier * tier.getPrice();
                remaining -= usageInTier;
            } else {
                usageInTier = remaining;
                cost += usageInTier * tier.getPrice();
                remaining = 0;
            }
            if (remaining <= 0) {
                break;
            }
        }
        return cost;
    }


    public Response<UsageHistory> saveUsage(int eUsed, LocalDate date, int userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<UsageHistory> bill = findBillByUserIdAndMonth(userId, date.getMonthValue());
        if(bill.isEmpty()){
            if (user.isPresent()) {
                double cost = calculateCost(eUsed);
                UsageHistory usageHistory = new UsageHistory(date, eUsed, cost, user.get(),0);
                log.info("Usage bill in {} has cost {} of user has id {} is saved", date.getMonthValue(), cost, userId);
                return ResponseFactory.successWithData(usageHistory);
            } else {
                log.warn("Create usage bill fail !! User has ID: {} not found.", userId);
                throw new UserNotFoundException("Create usage bill fail !! User has ID: " + userId +" not found.");
            }
        } else {
            return ResponseFactory.errorWithData(HttpStatus.BAD_REQUEST, "Invalid request", null);
        }
    }

    public Optional<UsageHistory> findBillByUserIdAndMonth(Integer id, int month){
        return usageHistoryRepository.findElectricityBillByUserIdAndMonth(id, month);
    }

    public List<UsageHistory> getAllUsageHistory() {
        return usageHistoryRepository.findAll();
    }

    public UsageHistory deleteUsageHistoryById(Long id) {
        Optional<UsageHistory> usageHistoryOptional = usageHistoryRepository.findById(id);
        if (usageHistoryOptional.isPresent()) {
            UsageHistory usageHistory = usageHistoryOptional.get();
            usageHistoryRepository.deleteById(id);
            log.info("Usage bill has id {} is deleted", id);
            return usageHistory;
        } else {
            throw new RuntimeException("Not found usage history has id : " + id);
        }
    }

    public UsageHistory updateUsageHistory(Long id, UsageHistory updatedUsageHistory) {
        Optional<UsageHistory> usageHistoryOptional = usageHistoryRepository.findById(id);
        if (usageHistoryOptional.isPresent()) {
            UsageHistory existingUsageHistory = usageHistoryOptional.get();

            existingUsageHistory.setDate(updatedUsageHistory.getDate());
            existingUsageHistory.seteUsed(updatedUsageHistory.geteUsed());
            existingUsageHistory.setAmount(updatedUsageHistory.getAmount());

            log.info("Usage bill in {} has cost {} of user has id {} is updated", existingUsageHistory.getDate().getMonthValue(), existingUsageHistory.getAmount(), existingUsageHistory.getUser().getId());
            return usageHistoryRepository.save(existingUsageHistory);
        } else {
            throw new RuntimeException("Không tìm thấy UsageHistory với ID: " + id);
        }
    }

    public List<UsageHistory> getUsageHistoryByUserId(Integer id) {
        return usageHistoryRepository.findAllByUserId(id);
    }

    public List<UsageHistory> getUsageHistoryHasNotPaiByIdUser(Integer id){
        return usageHistoryRepository.findAllByUserIdAndNotPaid(id);
    }

    @Transactional
    public UsageHistory payElectricityBillHasNotPaidByUserIdAndMonth(Integer id, int month){
        usageHistoryRepository.payElectricityBillHasNotPaidByUserIdAndMonth(id, month);
        UsageHistory billHadPaid = usageHistoryRepository.findElectricityBillHasPaidByUserIdAndMonth(id, month);
        return billHadPaid;
    }
}
