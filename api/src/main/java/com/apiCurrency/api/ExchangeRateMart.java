package com.apiCurrency.api;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "exchange_rate_mart")
@Getter
@Setter
public class ExchangeRateMart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String currency;
    private String name;
    private Double exchangeRate;

    // Constructors, getters, and setters

    public ExchangeRateMart() {
    }

    public ExchangeRateMart(Date date, String currency, String name, Double exchangeRate) {
        this.date = date;
        this.currency = currency;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

}
