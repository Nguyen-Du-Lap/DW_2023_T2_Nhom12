package org.transform.models;
import java.util.Date;

public class ExchangeRate {
    private int id;
    private Date date;
    private String currency;
    private String name;
    private Double exchangeRate;
    private Date dtChange;
    private Date dtExpired;

    // Constructors
    public ExchangeRate() {
        // Default constructor
    }

    public ExchangeRate(Date date, String currency, String name, Double exchangeRate, Date dtChange, Date dtExpired) {
        this.date = date;
        this.currency = currency;
        this.name = name;
        this.exchangeRate = exchangeRate;
        this.dtChange = dtChange;
        this.dtExpired = dtExpired;
    }

    // Getters and Setters (you can generate these using your IDE)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getDtChange() {
        return dtChange;
    }

    public void setDtChange(Date dtChange) {
        this.dtChange = dtChange;
    }

    public Date getDtExpired() {
        return dtExpired;
    }

    public void setDtExpired(Date dtExpired) {
        this.dtExpired = dtExpired;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", date=" + date +
                ", currency='" + currency + '\'' +
                ", name='" + name + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", dtChange=" + dtChange +
                ", dtExpired=" + dtExpired +
                '}';
    }
}
