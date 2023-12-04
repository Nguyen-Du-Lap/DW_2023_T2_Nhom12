package org.transform.models;

public class ExchangeRateFact {
    private int id;
    private int idCurrency;
    private int idName;
    private int idDate;
    private double exchangeRate;
    private int dtChange;
    private int dtExpired;

    // Constructors
    public ExchangeRateFact() {
        // Default constructor
    }

    public ExchangeRateFact(int idCurrency, int idName, int idDate, double exchangeRate, int dtChange, int dtExpired) {
        this.idCurrency = idCurrency;
        this.idName = idName;
        this.idDate = idDate;
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

    public int getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    public int getIdName() {
        return idName;
    }

    public void setIdName(int idName) {
        this.idName = idName;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getDtChange() {
        return dtChange;
    }

    public void setDtChange(int dtChange) {
        this.dtChange = dtChange;
    }

    public int getDtExpired() {
        return dtExpired;
    }

    public void setDtExpired(int dtExpired) {
        this.dtExpired = dtExpired;
    }

    @Override
    public String toString() {
        return "ExchangeRateFact{" +
                "id=" + id +
                ", idCurrency=" + idCurrency +
                ", idName=" + idName +
                ", idDate=" + idDate +
                ", exchangeRate=" + exchangeRate +
                ", dtChange=" + dtChange +
                ", dtExpired=" + dtExpired +
                '}';
    }
}
