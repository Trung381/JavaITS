package com.example.vuvantrung.controller;

import com.example.vuvantrung.entity.TierConfig;
import com.example.vuvantrung.service.TierConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-config")
public class TierConfigController {
    @Autowired
    private TierConfigService tierConfigService;

    @PostMapping("add")
    public ResponseEntity<TierConfig> createTierConfig(@RequestBody TierConfig tierConfig){
        return ResponseEntity.ok(tierConfigService.createTierConfig(tierConfig));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<TierConfig>> getAllTierConfigs(){
        return ResponseEntity.ok(tierConfigService.getAllTierConfigs());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<TierConfig> deleteTierConfigById(@PathVariable Long id){
        return ResponseEntity.ok(tierConfigService.deleteTierConfigById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<TierConfig> updateTierConfig(@PathVariable Long id, @RequestBody TierConfig updatedTierConfig) {
        return ResponseEntity.ok(tierConfigService.updateTierConfig(id, updatedTierConfig));
    }
}
