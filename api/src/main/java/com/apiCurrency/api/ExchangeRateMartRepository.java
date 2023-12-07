package com.apiCurrency.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExchangeRateMartRepository  extends JpaRepository<ExchangeRateMart, Integer> {
    List<ExchangeRateMart> findByDate(Date date);
    @Query("SELECT erm FROM ExchangeRateMart erm " +
            "WHERE erm.name = :nameCurrency " +
            "AND erm.date BETWEEN :dateStart AND :dateEnd " +
            "ORDER BY erm.date ASC")
    List<ExchangeRateMart> findERByCurrencyDateStartEnd(
            @Param("nameCurrency") String nameCurrency,
            @Param("dateStart") Date dateStart,
            @Param("dateEnd") Date dateEnd
    );
}
