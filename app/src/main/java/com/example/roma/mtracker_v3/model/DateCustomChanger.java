package com.example.roma.mtracker_v3.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Roma on 11.07.2017.
 */

public class DateCustomChanger {
    private Date date;
    Calendar mCalendar;
    private String[] monthArray = {"Янв", "Фвр", "Мрт", "Апр", "Мая", "Июня","Июля", "Авг", "Сент", "Окт", "Нояб", "Дек"};
    private String month;
    private String[] dayOfWeekArray = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
    private String dayOfWeek;
    private int day;


    public DateCustomChanger() {

    }

    public DateCustomChanger(Date date) {
        this.date = date;
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
    }


    public String getDay() {
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day);
    }

    public String getDayOfWeek() {
        dayOfWeek = dayOfWeekArray[mCalendar.get(Calendar.DAY_OF_WEEK)];
        return dayOfWeek;
    }

    public String getMonth() {
        month = monthArray[mCalendar.get(Calendar.MONTH)];
        return month;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
    }
}
