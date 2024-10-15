package com.example.vuvantrung.Controller;

import com.example.vuvantrung.Entity.UsageHistory;
import com.example.vuvantrung.Entity.User;
import com.example.vuvantrung.Repository.UsageHistoryRepository;
import com.example.vuvantrung.Service.UsageHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/UsageHistory")
public class UsageHistoryController {
    @Autowired
    UsageHistoryService usageHistoryService;

    @PostMapping("/create_usage_history_by_admin")
    public ResponseEntity<UsageHistory> createUsageHistoryByAdmin(@RequestBody Map<String, Object> request){
        int eUsed = (int) request.get("eUsed");
        LocalDate date = LocalDate.parse((String) request.get("date"));
        int userId = (int) request.get("userId");

        UsageHistory saved = usageHistoryService.saveUsage(eUsed, date, userId);
        log.info("new usage history of user with id: {} has been added", userId);
        return ResponseEntity.ok(saved);

    }

    @GetMapping
    public ResponseEntity<List<UsageHistory>> getAllUsageHistory(){
        return ResponseEntity.ok(usageHistoryService.getAllUsageHistory());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsageHistory> deleteUsageHistoryById(@PathVariable Long id){
        try{
            UsageHistory deletedHistory = usageHistoryService.deleteUsageHistoryById(id);
            log.info("deleted usage history has id {}", id);
            return ResponseEntity.ok(deletedHistory);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsageHistory> updateUsageHistory(@PathVariable Long id, @RequestBody UsageHistory updatedUsageHistory) {
        try {
            UsageHistory updatedHistory = usageHistoryService.updateUsageHistory(id, updatedUsageHistory);
            log.info("usage history has id {} is updated", id);
            return ResponseEntity.ok(updatedHistory);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UsageHistory>> getUsageHistoryByUsername(@PathVariable Integer id) {
        List<UsageHistory> usageHistories = usageHistoryService.getUsageHistoryByUsername(id);
        if (!usageHistories.isEmpty()) {
            return ResponseEntity.ok(usageHistories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
