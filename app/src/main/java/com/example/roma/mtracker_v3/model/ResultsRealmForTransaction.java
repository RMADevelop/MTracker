package com.example.roma.mtracker_v3.model;

import com.example.roma.mtracker_v3.Adapters.TransactionAdapter;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Roma on 27.07.2017.
 */

public class ResultsRealmForTransaction {
    private DateCustomChanger dateCustom = new DateCustomChanger();
    private Date dateNow;

    public static final int RESULT_COMMON = 0;
    //    public static final int RESULT_COMMON = 1;
//    public static final int RESULT_COMMON = 2;
//    public static final int RESULT_COMMON = 3;
//    public static final int RESULT_COMMON = 4;
    Realm realm;
    private RealmResults<TransactionItem> realmResults;

    public ResultsRealmForTransaction(Realm realm) {
        this.realm = realm;
        dateNow = new Date();
    }

    public RealmResults<TransactionItem> getResultCommon(Realm realm, boolean monthThis) {
        if (monthThis) {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(0))
                    .equalTo("completeStatus", false)
                    .equalTo("failedStatus", false)
                    .findAll()
                    .sort("date", Sort.ASCENDING);
        } else {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(0))
                    .equalTo("completeStatus", false)
                    .equalTo("failedStatus", false)
                    .findAll()
                    .sort("date", Sort.ASCENDING);
        }
        return realmResults;

    }

    public RealmResults<TransactionItem> getResultAll(Realm realm, boolean monthThis) {
        if (monthThis) {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(0))
                    .findAll()
                    .sort("date", Sort.ASCENDING);
        } else {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(0))
                    .findAll()
                    .sort("date", Sort.ASCENDING);
        }
        return realmResults;

    }


    public RealmResults<TransactionItem> getResultCustom(Realm realm, boolean monthThis, boolean completeCB, boolean failedCB) {
        if (monthThis) {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(0))
                    .beginGroup()
                    .equalTo("completeStatus", completeCB)
                    .or()
                    .equalTo("failedStatus", failedCB)
                    .endGroup()
                    .findAll()
                    .sort("date", Sort.ASCENDING);

        } else {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(0))
                    .beginGroup()
                    .equalTo("completeStatus", completeCB)
                    .or()
                    .equalTo("failedStatus", failedCB)
                    .endGroup()
                    .findAll()
                    .sort("date", Sort.ASCENDING);
        }
        return realmResults;
    }

    public RealmResults<TransactionItem> getResultAllInMonth(Realm realm, boolean monthThis) {

        if (monthThis) {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getThisMonthWithStartEnd(0))
                    .findAll()
                    .sort("date", Sort.ASCENDING);
        } else {
            realmResults = realm.where(TransactionItem.class)
                    .lessThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(31))
                    .greaterThanOrEqualTo("date", dateCustom.getNextMonthWithStartAndEnd(0))
                    .findAll()
                    .sort("date", Sort.ASCENDING);
        }
        return realmResults;
    }
}
