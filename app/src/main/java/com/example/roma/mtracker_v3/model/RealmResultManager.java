package com.example.roma.mtracker_v3.model;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Roma on 22.07.2017.
 */

public class RealmResultManager {

    RealmResults<Item> result;
    Realm realm;
    DateCustomChanger dateCustom;

    public RealmResultManager(Realm realm) {
        dateCustom = new DateCustomChanger(new Date());
        this.realm = realm;
    }
    public RealmResults<Item> getResultMonthThis() {
        result = realm.where(Item.class).greaterThanOrEqualTo("mDate", dateCustom.getThisMonthWithStartEnd(31)).findAll();
        return result;
    }


}
