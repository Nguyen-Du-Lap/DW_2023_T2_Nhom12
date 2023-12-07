package com.apiCurrency.api.controllers;

import com.apiCurrency.api.ExchangeRateMart;
import com.apiCurrency.api.ExchangeRateMartRepository;
import com.apiCurrency.api.YourRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/exchangeRateMart")
public class ExchangeRateMartController {
    @Autowired
    ExchangeRateMartRepository repo;
    @GetMapping
    public List<ExchangeRateMart> getExchangeRatesByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return repo.findByDate(date);
    }
    @PostMapping
    public List<ExchangeRateMart> getERByCurrencyDateStartEnd(@RequestBody YourRequestDTO requestDTO) {
        String startDate = requestDTO.getDateStart();
        String endDate = requestDTO.getDateEnd();
        String nameCurrency = requestDTO.getNameCurrency();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart = dateFormat.parse(startDate);
            dateEnd = dateFormat.parse(endDate);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
            // Hoặc throw một exception phù hợp
        }
        return repo.findERByCurrencyDateStartEnd(nameCurrency, dateStart, dateEnd);
    }
}
