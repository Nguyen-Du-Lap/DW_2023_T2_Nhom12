package com.apiCurrency.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YourRequestDTO {
    private String dateStart;
    private String dateEnd;
    private String nameCurrency;

    public YourRequestDTO(String dateStart, String dateEnd, String nameCurrency) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.nameCurrency = nameCurrency;
    }
}
