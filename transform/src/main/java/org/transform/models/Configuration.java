package org.transform.models;

import java.util.Date;

public class Configuration {
    private String sourcePath;
    private String location;
    private String format;
    private Date dateRun;

    // Constructors, getters, and setters

    public Configuration() {
        // Default constructor
    }

    public Configuration(String sourcePath, String location, String format, Date dateRun) {
        this.sourcePath = sourcePath;
        this.location = location;
        this.format = format;
        this.dateRun = dateRun;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getDateRun() {
        return dateRun;
    }

    public void setDateRun(Date dateRun) {
        this.dateRun = dateRun;
    }
}