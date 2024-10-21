package com.example.vuvantrung.controller;

import com.example.vuvantrung.DTO.Response;
import com.example.vuvantrung.entity.UsageHistory;
import com.example.vuvantrung.service.UsageHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/UsageHistory")
public class UsageHistoryController {
    @Autowired
    UsageHistoryService usageHistoryService;

    @PostMapping("/create_usage_history")
    public Response<UsageHistory> createUsageHistoryByAdmin(@RequestBody Map<String, Object> request) {

        return usageHistoryService.saveUsage(
                (int) request.get("eUsed"),
                LocalDate.parse((String) request.get("date")),
                (int) request.get("userId")
        );
    }

    @GetMapping("/get_all_history")
    public ResponseEntity<List<UsageHistory>> getAllUsageHistory() {
        return ResponseEntity.ok(usageHistoryService.getAllUsageHistory());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<UsageHistory> deleteUsageHistoryById(@PathVariable Long id) {
        return ResponseEntity.ok(usageHistoryService.deleteUsageHistoryById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UsageHistory> updateUsageHistory(@PathVariable Long id, @RequestBody UsageHistory updatedUsageHistory) {
        return ResponseEntity.ok(usageHistoryService.updateUsageHistory(id, updatedUsageHistory));
    }

    @GetMapping("/usage_history_by_user_id/{id}")
    public ResponseEntity<List<UsageHistory>> getUsageHistoryByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(usageHistoryService.getUsageHistoryByUserId(id));
    }

    @GetMapping("/usage_history_not_paid_by_user_id/{id}")
    public ResponseEntity<List<UsageHistory>> getUsageHistoryNotPaidByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(usageHistoryService.getUsageHistoryHasNotPaiByIdUser(id));
    }

    @GetMapping("/pay_electric_bill_by_id_user_and_month")
    public ResponseEntity<UsageHistory> payElectricBillByIdUserAndMonth(@RequestParam Integer id, @RequestParam int month) {
        return ResponseEntity.ok(usageHistoryService.payElectricityBillHasNotPaidByUserIdAndMonth(id, month));
    }


}
