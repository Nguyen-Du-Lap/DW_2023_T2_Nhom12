package org.transform.models;

import java.util.Date;

public class CurrencyDim {
    private int id;
    private String nameCurrency;
    private Date dtChange;
    private Date dtExpired;

    // Constructors
    public CurrencyDim() {
        // Default constructor
    }

    public CurrencyDim(String nameCurrency, Date dtChange, Date dtExpired) {
        this.nameCurrency = nameCurrency;
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

    public String getNameCurrency() {
        return nameCurrency;
    }

    public void setNameCurrency(String nameCurrency) {
        this.nameCurrency = nameCurrency;
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
        return "CurrencyDim{" +
                "id=" + id +
                ", nameCurrency='" + nameCurrency + '\'' +
                ", dtChange=" + dtChange +
                ", dtExpired=" + dtExpired +
                '}';
    }
}
