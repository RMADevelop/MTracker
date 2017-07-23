package com.example.roma.mtracker_v3.model;

import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.roma.mtracker_v3.Adapters.MonthThisAdapter;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class SpinnerMonthThis {
    public static final int MONTH_THIS = 0;
    public static final int MONTH_PREV = 1;
    public static final int MONTH_CHOOS = 2;
    public static final int MONTH_ALL = 3;

    //    Realm realm;
    DateCustomChanger mCustomChanger;

    private DateCustomChanger monthNow;
    private Date monthPrev;
    private Date monthChoose;
    private Spinner spinner;

    private Date dateNow;
    RealmResults<Item> resultItem;

    public SpinnerMonthThis(Spinner spinner) {
        this.spinner = spinner;
//        realm = Realm.getDefaultInstance();
        dateNow = new Date();
        mCustomChanger = new DateCustomChanger();
        monthNow = new DateCustomChanger(dateNow);
    }

    public RealmResults<Item> getRealmResult(Realm realm, int id) {
        Log.v("SPINNERTEST", "getRealResult");

        switch (id) {
            case MONTH_THIS:
                getThisMonth(realm);
                Log.v("TESTYTDIFGSDFB", " size1 " + resultItem.size());

                break;
            case MONTH_PREV:
                getPrevMonth(realm);

                Log.v("TESTYTDIFGSDFB", " size2 " + resultItem.size());

                break;
            default:
                resultItem = realm.where(Item.class).greaterThanOrEqualTo("mDate", monthNow.getThisMonthWithStartEnd(1)).findAll();
                Log.v("TESTYTDIFGSDFB", " size3 " + resultItem.size());

                break;
        }
        return resultItem;
    }




    private RealmResults<Item> getPrevMonth(Realm realm) {
        resultItem = realm.where(Item.class)
                .lessThanOrEqualTo("mDate", mCustomChanger.getPrevMonthWithStartEnd(31))
                .greaterThanOrEqualTo("mDate", mCustomChanger.getPrevMonthWithStartEnd(0))
                .findAll()
                .sort("mDate", Sort.DESCENDING);
        return resultItem;
    }

    private RealmResults<Item> getThisMonth(Realm realm) {
        resultItem = realm.where(Item.class)
                .lessThanOrEqualTo("mDate", mCustomChanger.getThisMonthWithStartEnd(31))
                .greaterThanOrEqualTo("mDate", mCustomChanger.getThisMonthWithStartEnd(0))
                .findAll()
                .sort("mDate", Sort.DESCENDING);
        return resultItem;
    }
}
