package org.transform.models;

import java.util.Date;

public class NameDim {
    private int id;
    private String name;
    private Date dtChange;
    private Date dtExpired;

    // Constructors
    public NameDim() {
        // Default constructor
    }

    public NameDim(String name, Date dtChange, Date dtExpired) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "NameDim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dtChange=" + dtChange +
                ", dtExpired=" + dtExpired +
                '}';
    }
}
