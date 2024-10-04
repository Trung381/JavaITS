package com.example.vuvantrung.Controller;

import com.example.vuvantrung.Entity.UsageHistory;
import com.example.vuvantrung.Repository.UsageHistoryRepository;
import com.example.vuvantrung.Service.UsageHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UsageHistory")
public class UsageHistoryController {
    @Autowired
    UsageHistoryService usageHistoryService;

    @PostMapping
    public ResponseEntity<UsageHistory> createUsageHistory(@RequestBody Map<String, Object> request){
        int eUsed = (int) request.get("eUsed");
        LocalDate date = LocalDate.parse((String) request.get("date"));

        UsageHistory saved = usageHistoryService.caculateAndSaveUSage(eUsed, date);
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
            return ResponseEntity.ok(updatedHistory);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
