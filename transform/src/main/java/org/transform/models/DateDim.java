package org.transform.models;

import java.util.Date;

public class DateDim {
    private int id;
    private Date date;
    private Integer dayOfWeek;
    private Integer dayNumberInMonth;
    private String dayName;
    private String monthName;
    private Integer year;
    private String monthYear;
    private Integer weekNumber;
    private Integer dayNumberInYear;
    private Integer weekdayFlag;
    private String weekInYear;
    private Date startOfWeek;
    private Integer weekNumberPreviousYear;
    private String startOfWeekPreviousYear;
    private Date endOfWeekPreviousYear;
    private String quarter;
    private Integer holidayFlag;
    private String holidayDescription;
    private String weekendType;

    // Constructors
    public DateDim() {
        // Default constructor
    }

    public DateDim(Date date, Integer dayOfWeek, Integer dayNumberInMonth, String dayName, String monthName,
                   Integer year, String monthYear, Integer weekNumber, Integer dayNumberInYear, Integer weekdayFlag,
                   String weekInYear, Date startOfWeek, Integer weekNumberPreviousYear, String startOfWeekPreviousYear,
                   Date endOfWeekPreviousYear, String quarter, Integer holidayFlag, String holidayDescription,
                   String weekendType) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.dayNumberInMonth = dayNumberInMonth;
        this.dayName = dayName;
        this.monthName = monthName;
        this.year = year;
        this.monthYear = monthYear;
        this.weekNumber = weekNumber;
        this.dayNumberInYear = dayNumberInYear;
        this.weekdayFlag = weekdayFlag;
        this.weekInYear = weekInYear;
        this.startOfWeek = startOfWeek;
        this.weekNumberPreviousYear = weekNumberPreviousYear;
        this.startOfWeekPreviousYear = startOfWeekPreviousYear;
        this.endOfWeekPreviousYear = endOfWeekPreviousYear;
        this.quarter = quarter;
        this.holidayFlag = holidayFlag;
        this.holidayDescription = holidayDescription;
        this.weekendType = weekendType;
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

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayNumberInMonth() {
        return dayNumberInMonth;
    }

    public void setDayNumberInMonth(Integer dayNumberInMonth) {
        this.dayNumberInMonth = dayNumberInMonth;
    }

    // ... (Continue with the rest of the getters and setters)

    @Override
    public String toString() {
        return "DateDim{" +
                "id=" + id +
                ", date=" + date +
                ", dayOfWeek=" + dayOfWeek +
                ", dayNumberInMonth=" + dayNumberInMonth +
                ", dayName='" + dayName + '\'' +
                ", monthName='" + monthName + '\'' +
                ", year=" + year +
                ", monthYear='" + monthYear + '\'' +
                ", weekNumber=" + weekNumber +
                ", dayNumberInYear=" + dayNumberInYear +
                ", weekdayFlag=" + weekdayFlag +
                ", weekInYear='" + weekInYear + '\'' +
                ", startOfWeek=" + startOfWeek +
                ", weekNumberPreviousYear=" + weekNumberPreviousYear +
                ", startOfWeekPreviousYear='" + startOfWeekPreviousYear + '\'' +
                ", endOfWeekPreviousYear=" + endOfWeekPreviousYear +
                ", quarter='" + quarter + '\'' +
                ", holidayFlag=" + holidayFlag +
                ", holidayDescription='" + holidayDescription + '\'' +
                ", weekendType='" + weekendType + '\'' +
                '}';
    }
}
