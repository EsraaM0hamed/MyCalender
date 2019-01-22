package com.desai.vatsal.mydynamiccalendarexample;

import java.util.Date;

/**
 * Created by esraa on 1/13/19.
 */

public class DateModelE {
    private String name;
    private int flag;
    private String date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public DateModelE(String name, String type, String date, int event_view) {

        this.name = name;
        this.type = type;
        this.date = date;
        this.flag = event_view;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDates() {
        return name;
    }

    public void setDates(String dates) {
        this.name = dates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
