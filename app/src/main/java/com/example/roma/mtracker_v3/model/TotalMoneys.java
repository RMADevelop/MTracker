package com.example.roma.mtracker_v3.model;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by Roma on 28.07.2017.
 */

public class TotalMoneys {
    private RealmResults<TransactionItem> resultTransaction;
    private RealmResults<Item> resultItem;
    private Realm realm;
    private ResultsRealmForTransaction mResultsRealmForTransaction;

    public TotalMoneys(Realm realm) {
        this.realm = realm;
        mResultsRealmForTransaction = new ResultsRealmForTransaction(realm);

    }


    public int getTotalValue(boolean thisMonth) {
        resultTransaction = mResultsRealmForTransaction.getResultAll(realm, thisMonth);

        if (resultTransaction == null)
            return 0;
        int total = 0;
        for (TransactionItem item : resultTransaction) {
            total += item.getValue();
        }


        return total;
    }

    public int getTotalValueLost(boolean thisMonth,boolean failedCB) {
        resultTransaction = mResultsRealmForTransaction.getResultAll(realm, thisMonth);
        if (resultTransaction == null)
            return 0;
        int total = 0;
        for (TransactionItem item : resultTransaction) {
            if (failedCB) {
                if (!item.isCompleteStatus()) {
                    total += item.getValue();
                }
            } else {
                if (!item.isCompleteStatus() && !item.isFailedStatus()) {
                    total += item.getValue();
                }
            }

        }
        return total;
    }


    public RealmResults<TransactionItem> getResultTransaction() {
        return resultTransaction;
    }

    public void setResultTransaction(RealmResults<TransactionItem> resultTransaction) {
        this.resultTransaction = resultTransaction;
    }

    public RealmResults<Item> getResultItem() {
        return resultItem;
    }

    public void setResultItem(RealmResults<Item> resultItem) {
        this.resultItem = resultItem;
    }
}
