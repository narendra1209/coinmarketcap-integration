package com.cashrich1.controller;

import com.cashrich1.entity.CoinData;
import com.cashrich1.service.CoinDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coins")
public class CoinDataController {

    @Autowired
    private CoinDataService coinDataService;

    @GetMapping("/{userId}/{symbols}")
    public ResponseEntity<CoinData> getCoinData(@PathVariable Long userId, @PathVariable String symbols) {
        CoinData coinData = coinDataService.fetchCoinData(userId, symbols);
        return ResponseEntity.ok(coinData);
    }
}
