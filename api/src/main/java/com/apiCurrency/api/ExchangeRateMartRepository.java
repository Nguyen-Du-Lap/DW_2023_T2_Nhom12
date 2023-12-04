package com.apiCurrency.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateMartRepository  extends JpaRepository<ExchangeRateMart, Long> {
}
