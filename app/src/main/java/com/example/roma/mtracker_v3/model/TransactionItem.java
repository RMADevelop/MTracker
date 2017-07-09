package com.example.roma.mtracker_v3.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Roma on 02.07.2017.
 */

public class TransactionItem extends RealmObject {
    private int value;
    private String description;
    private int pl_mn;
    private Date date;
    private int idImages;

    public TransactionItem() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPl_mn() {
        return pl_mn;
    }

    public void setPl_mn(int pl_mn) {
        this.pl_mn = pl_mn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdImages() {
        return idImages;
    }

    public void setIdImages(int idImages) {
        this.idImages = idImages;
    }
}
