package com.apiCurrency.api.controllers;

import com.apiCurrency.api.NameDim;
import com.apiCurrency.api.NameDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/name-dim")
public class NameDimController {
    @Autowired
    private NameDimRepository namedim;

    @GetMapping
    public ResponseEntity<List<NameDim>> findAll() {
        return new ResponseEntity<>(namedim.findAll(), HttpStatus.OK);
    }
}
