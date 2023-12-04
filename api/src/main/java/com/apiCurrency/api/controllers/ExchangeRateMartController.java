package com.apiCurrency.api.controllers;

import com.apiCurrency.api.ExchangeRateMart;
import com.apiCurrency.api.ExchangeRateMartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exchangeRateMart")
public class ExchangeRateMartController {
    @Autowired
    ExchangeRateMartRepository repo;
    @GetMapping
    public ResponseEntity<List<ExchangeRateMart>> getAll() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }
}
