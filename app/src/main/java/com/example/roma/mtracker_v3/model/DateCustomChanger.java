package com.example.roma.mtracker_v3.model;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Roma on 11.07.2017.
 */

public class DateCustomChanger {
    public static final int MONTH_SHORT = 0;
    public static final int MONTH_FULL = 1;


    private Date date;
    Calendar mCalendar;
    private String[] monthShortArray = {"Янв", "Фвр", "Мрт", "Апр", "Мая", "Июня", "Июля", "Авг", "Сент", "Окт", "Нояб", "Дек"};
    private String[] monthFullArray = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private String month;
    private String[] dayOfWeekArray = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
    private String dayOfWeek;
    private int day;


    public DateCustomChanger() {
        date = new Date();
        mCalendar = Calendar.getInstance();
    }

    public DateCustomChanger(Date date) {
        this.date = date;
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
    }

    public DateCustomChanger(int dateIn) {
        this.date = new Date(dateIn);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
    }


    public Date getThisMonthWithStartEnd(int day) {
        Calendar calendar1 = Calendar.getInstance();
        Date dateTemp = new Date();

        calendar1.set(Calendar.DAY_OF_MONTH, day);

        dateTemp.setTime(calendar1.getTimeInMillis());

        return dateTemp;

    }

    public Date getPrevMonthWithStartEnd(int day) {
        Calendar calendar = Calendar.getInstance();
        Date dateTemp = new Date();

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        dateTemp.setTime(calendar.getTimeInMillis());


        return dateTemp;
    }
    public Date getNextMonthWithStartAndEnd(int day) {
        Calendar calendar = Calendar.getInstance();
        Date dateTemp = new Date();

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        dateTemp.setTime(calendar.getTimeInMillis());


        return dateTemp;
    }

    public void setDatePrev() {
        Calendar calendar = Calendar.getInstance();
        Date dateTemp = new Date();

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        dateTemp.setTime(calendar.getTimeInMillis());

        date = dateTemp;
        mCalendar.setTime(date);
    }

    public void setDateNow() {
        date = new Date();
        mCalendar = Calendar.getInstance();
    }

    public String getDay() {
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day);
    }

    public String getDayOfWeek() {
        dayOfWeek = dayOfWeekArray[mCalendar.get(Calendar.DAY_OF_WEEK)];
        return dayOfWeek;
    }

    public String getMonth(int id) {
        switch (id) {
            case MONTH_SHORT:
                month = monthShortArray[mCalendar.get(Calendar.MONTH)];
                break;
            case MONTH_FULL:
                month = monthFullArray[mCalendar.get((Calendar.MONTH))];
                break;
        }
        Log.v("DATEEMONTH", month);
        return month;
    }

    public int getYear() {
        int year = mCalendar.get(Calendar.YEAR);
        return year;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
    }
//    public Date getDateWithNextDay (Date date) {
//        mCalendar.setTime(date);
//
//    }

    public Calendar getCalendar() {
        return mCalendar;
    }
}
