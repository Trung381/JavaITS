package com.example.vuvantrung.controller;

import com.example.vuvantrung.dto.BaseResponse;
import com.example.vuvantrung.dto.request.UsageHistoryRequest;
import com.example.vuvantrung.entity.UsageHistory;
import com.example.vuvantrung.service.UsageHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/usage-history")
public class UsageHistoryController {
    @Autowired
    private UsageHistoryService usageHistoryService;

    // Endpoint để thêm UsageHistory
    @PostMapping("/add")
    public ResponseEntity<BaseResponse<UsageHistory>> createUsageHistoryByAdmin(@Valid @RequestBody UsageHistoryRequest request) {

        BaseResponse<UsageHistory> response = usageHistoryService.saveUsage(
                request.getUsed(),
                request.getDate(),
                request.getUserId()
        );

        if(response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Endpoint để lấy tất cả UsageHistory
    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse<List<UsageHistory>>> getAllUsageHistory() {
        List<UsageHistory> usageHistories = usageHistoryService.getAllUsageHistory();
        BaseResponse<List<UsageHistory>> response = BaseResponse.success(usageHistories, "Fetched all usage histories successfully.");
        return ResponseEntity.ok(response);
    }

    // Endpoint để xóa UsageHistory theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<UsageHistory>> deleteUsageHistoryById(@PathVariable Long id) {
        UsageHistory deletedUsageHistory = usageHistoryService.deleteUsageHistoryById(id);
        BaseResponse<UsageHistory> response = BaseResponse.success(deletedUsageHistory, "Usage history deleted successfully.");
        return ResponseEntity.ok(response);
    }

    // Endpoint để cập nhật UsageHistory
    @PostMapping("/update/{id}")
    public ResponseEntity<BaseResponse<UsageHistory>> updateUsageHistory(@PathVariable Long id, @RequestBody UsageHistory updatedUsageHistory) {
        UsageHistory usageHistory = usageHistoryService.updateUsageHistory(id, updatedUsageHistory);
        BaseResponse<UsageHistory> response = BaseResponse.success(usageHistory, "Usage history updated successfully.");
        return ResponseEntity.ok(response);
    }

    // Endpoint để lấy UsageHistory theo userId
    @GetMapping("/usage-history-by-user-id/{id}")
    public ResponseEntity<BaseResponse<List<UsageHistory>>> getUsageHistoryByUserId(@PathVariable Integer id) {
        List<UsageHistory> usageHistories = usageHistoryService.getUsageHistoryByUserId(id);
        BaseResponse<List<UsageHistory>> response = BaseResponse.success(usageHistories, "Fetched usage histories by user ID successfully.");
        return ResponseEntity.ok(response);
    }

    // Endpoint để lấy UsageHistory chưa thanh toán theo userId
    @GetMapping("/usage-history-not-paid-by-user-id/{id}")
    public ResponseEntity<BaseResponse<List<UsageHistory>>> getUsageHistoryNotPaidByUserId(@PathVariable Integer id){
        List<UsageHistory> usageHistories = usageHistoryService.getUsageHistoryHasNotPaiByIdUser(id);
        BaseResponse<List<UsageHistory>> response = BaseResponse.success(usageHistories, "Fetched unpaid usage histories by user ID successfully.");
        return ResponseEntity.ok(response);
    }

    // Endpoint để thanh toán hóa đơn điện
    @GetMapping("/pay-electric-bill-by-id-user-and-month")
    public ResponseEntity<BaseResponse<UsageHistory>> payElectricBillByIdUserAndMonth(@RequestParam Integer id, @RequestParam int month) {
        UsageHistory paidUsageHistory = usageHistoryService.payElectricityBillHasNotPaidByUserIdAndMonth(id, month);
        BaseResponse<UsageHistory> response = BaseResponse.success(paidUsageHistory, "Electric bill paid successfully.");
        return ResponseEntity.ok(response);
    }
}
