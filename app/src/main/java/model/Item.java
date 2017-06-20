package model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;



public class Item extends RealmObject {
    public final static String PLMN_ROW = "pl_mn";


    private String mUUID;
    private int value;
    private int idDescription;
    private int pl_mn;
    private Date mDate;

    public Item() {
        this.mUUID = UUID.randomUUID().toString();
        this.mDate = new Date();
    }

    public Item(String id) {
        this.mUUID = id;
    }

    public String getUUID() {
        return mUUID;
    }

    public void setUUID(String id) {
        mUUID = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIdDescription() {
        return idDescription;
    }

    public void setIdDescription(int idDescription) {
        this.idDescription = idDescription;
    }

    public int getPl_mn() {
        return pl_mn;
    }

    public void setPl_mn(int pl_mn) {
        this.pl_mn = pl_mn;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
