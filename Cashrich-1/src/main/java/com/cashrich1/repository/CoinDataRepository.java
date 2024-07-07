package com.cashrich1.repository;

import com.cashrich1.entity.CoinData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinDataRepository extends JpaRepository<CoinData, Long> {
    List<CoinData> findByUserId(Long userId);
}
