package model;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Roma on 26.04.2017.
 */

public class Singleton {

    private static Singleton sSinglet;
    Realm mRealm;

    public static Singleton get() {
        if (sSinglet == null) {
            sSinglet = new Singleton();
        }
        return sSinglet;
    }


    private Singleton() {
        mRealm = Realm.getDefaultInstance();
    }

    public void addItem(int pl_mn, int value, int idDescription, Date date) {
        final Item item = new Item();
        item.setValue(value);
        item.setPl_mn(pl_mn);
        item.setDate(date);
        item.setIdDescription(idDescription);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealm(item);
            }
        });
    }

    private RealmResults<Item> findAll() {
            return mRealm.where(Item.class).findAll();
    }

    private RealmResults<Item> findID() {
        return mRealm.where(Item.class).equalTo(Item.PLMN_ROW, "1").findAll();
    }




}
