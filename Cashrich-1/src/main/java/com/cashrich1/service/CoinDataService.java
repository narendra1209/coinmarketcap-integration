package com.cashrich1.service;

import com.cashrich1.entity.CoinData;
import com.cashrich1.repository.CoinDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CoinDataService {

    @Autowired
    private CoinDataRepository coinDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${coinmarketcap.api.url}")
    private String apiUrl;

    @Value("${coinmarketcap.api.key}")
    private String apiKey;

    public CoinData fetchCoinData(Long userId, String symbols) {
        String url = String.format("%s?symbol=%s", apiUrl, symbols);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            CoinData coinData = new CoinData();
            coinData.setUserId(userId);
            coinData.setSymbol(symbols);
            coinData.setData(response.getBody());
            coinData.setTimestamp(LocalDateTime.now());

            return coinDataRepository.save(coinData);
        } else {
            throw new RuntimeException("Failed to fetch coin data. Status code: " + response.getStatusCode());
        }
    }
}
