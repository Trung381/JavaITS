package com.example.vuvantrung.Service;

import com.example.vuvantrung.DTO.ApiResponse;
import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Entity.UsageHistory;
import com.example.vuvantrung.Entity.User;
import com.example.vuvantrung.Exception.UserNotFoundException;
import com.example.vuvantrung.Repository.UsageHistoryRepository;
import com.example.vuvantrung.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

//    public UsageHistory saveUsage(int eUsed, LocalDate date, int userId) {
//        Optional<User> user = userRepository.findById(userId);
//        Optional<UsageHistory> bill = findBillByUserIdAndMonth(userId, date.getMonthValue());
//        if(bill.isEmpty()){
//            if (user.isPresent()) {
//                double cost = calculateCost(eUsed);
//                UsageHistory usageHistory = new UsageHistory(date, eUsed, cost, user.get(),0);
//                return usageHistoryRepository.save(usageHistory);
//            } else {
//                throw new UserNotFoundException("Người dùng với ID: " + userId + " không tồn tại.");
//            }
//        } else {
//            return null;
//        }
//    }
public ApiResponse<UsageHistory> saveUsage(int eUsed, LocalDate date, int userId) {
    Optional<User> user = userRepository.findById(userId);
    Optional<UsageHistory> bill = findBillByUserIdAndMonth(userId, date.getMonthValue());
    if(bill.isEmpty()){
        if (user.isPresent()) {
            double cost = calculateCost(eUsed);
            UsageHistory usageHistory = new UsageHistory(date, eUsed, cost, user.get(),0);
            return new ApiResponse<>(true, "success", HttpStatus.OK ,usageHistory);
        } else {
            throw new UserNotFoundException("Người dùng với ID: " + userId + " không tồn tại.");
        }
    } else {
        return new ApiResponse<>(false, "Invalid request", HttpStatus.BAD_REQUEST, null);
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
            return usageHistory;
        } else {
            throw new RuntimeException("Lịch sử sử dụng điện không tồn tại với ID: " + id);
        }
    }

    public UsageHistory updateUsageHistory(Long id, UsageHistory updatedUsageHistory) {
        Optional<UsageHistory> usageHistoryOptional = usageHistoryRepository.findById(id);
        if (usageHistoryOptional.isPresent()) {
            UsageHistory existingUsageHistory = usageHistoryOptional.get();

            existingUsageHistory.setDate(updatedUsageHistory.getDate());
            existingUsageHistory.seteUsed(updatedUsageHistory.geteUsed());
            existingUsageHistory.setAmount(updatedUsageHistory.getAmount());

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

//    public Optional<List<UsageHistory>> payElectricityBillHasNotPaidByUserIdAndMonth(Integer id, int month) throws Exception {
//        usageHistoryRepository.payElectricityBillHasNotPaidByUserIdAndMonth(id, month);
//        Optional<List<UsageHistory>> billHadPaid = Optional.ofNullable(usageHistoryRepository.findElectricityBillHasPaidByUserIdAndMonth(id, month));
//        if(!billHadPaid.isPresent()){
//            throw new Exception("bill not found");
//        }
//        return billHadPaid;
//    }
    @Transactional
    public UsageHistory payElectricityBillHasNotPaidByUserIdAndMonth(Integer id, int month){
        usageHistoryRepository.payElectricityBillHasNotPaidByUserIdAndMonth(id, month);
        UsageHistory billHadPaid = usageHistoryRepository.findElectricityBillHasPaidByUserIdAndMonth(id, month);
        return billHadPaid;
    }
}
