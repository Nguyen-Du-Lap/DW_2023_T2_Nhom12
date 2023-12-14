package com.apiCurrency.api.controllers;

import com.apiCurrency.api.CurrencyDim;
import com.apiCurrency.api.CurrencyDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/currency-dim")
public class CurrencyDimController {
    @Autowired
    private CurrencyDimRepository currencyDim;
    @GetMapping
    public ResponseEntity<List<CurrencyDim>> findAll() {
        return new ResponseEntity<>(currencyDim.findAll(), HttpStatus.OK);
    }
}
