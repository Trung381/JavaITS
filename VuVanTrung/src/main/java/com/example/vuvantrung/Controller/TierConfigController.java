package com.example.vuvantrung.Controller;

import com.example.vuvantrung.Entity.TierConfig;
import com.example.vuvantrung.Service.TierConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TierConfig")
public class TierConfigController {
    @Autowired
    private TierConfigService tierConfigService;

    @PostMapping
    public ResponseEntity<TierConfig> createTierConfig(@RequestBody TierConfig tierConfig){
        TierConfig savedTier = tierConfigService.createTierConfig(tierConfig);
        return ResponseEntity.ok(savedTier);
    }

    @GetMapping
    public ResponseEntity<List<TierConfig>> getAllTierConfigs(){
        List<TierConfig> allTier = tierConfigService.getAllTierConfigs();
        return ResponseEntity.ok(allTier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TierConfig> deleteTierConfigById(@PathVariable Long id){
        try{
            TierConfig tierConfig = tierConfigService.deleteTierConfigById(id);
            return ResponseEntity.ok(tierConfig);
        }
        catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TierConfig> updateTierConfig(@PathVariable Long id, @RequestBody TierConfig updatedTierConfig) {
        try {
            TierConfig updatedConfig = tierConfigService.updateTierConfig(id, updatedTierConfig);
            return ResponseEntity.ok(updatedConfig);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

