package com.example.roma.mtracker_v3.model;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Roma on 22.07.2017.
 */

public class RealmResultManager {

    RealmResults<TransactionItem> result;
    Realm realm;
    DateCustomChanger dateCustom;
    TransactionItem transaction;
    int i = 0;

    public RealmResultManager(Realm realm) {
        dateCustom = new DateCustomChanger(new Date());
        this.realm = realm;
    }

    public void setResultForManager(RealmResults<TransactionItem> result) {
        if (result != null)
            this.result = result;
    }

    public void refreshStatusResults() {
        if (result != null) {
            for (i = 0; i < result.size(); i++) {
                if (result.get(i).getDate().getTime() + 10000000 < dateCustom.getDate().getTime() && !result.get(i).isCompleteStatus() ) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            result.get(i).setFailedStatus(true);
                        }
                    });
                } else {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            result.get(i).setFailedStatus(false);
                        }
                    });
                }
            }
        }
        i = 0;
    }


}
